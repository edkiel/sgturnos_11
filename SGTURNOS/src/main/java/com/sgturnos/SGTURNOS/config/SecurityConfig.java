package com.sgturnos.SGTURNOS.config;

import com.sgturnos.SGTURNOS.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Ya no inyectamos CustomUserDetailsService directamente aquí
    // Será inyectado en el DaoAuthenticationProvider Bean

    // Inyectamos el CustomUserDetailsService para usarlo en el proveedor de autenticación
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService); // Usamos el service inyectado
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // ELIMINAMOS ESTE MÉTODO. Ya no es necesario con la forma moderna de configurar el AuthenticationProvider
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(authenticationProvider());
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF temporalmente si estás en fase de pruebas (activar en producción)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/images/**", "/plugins/**", "/webjars/**", "/login", "/").permitAll() // Permitir acceso a recursos estáticos y página de login
                .requestMatchers("/admin/**").hasRole("ADMIN") // Solo ADMIN puede acceder a /admin
                .requestMatchers("/jefe/**").hasAnyRole("ADMIN", "JEFE_AREA") // ADMIN y JEFE_AREA a /jefe
                .anyRequest().authenticated() // Cualquier otra petición requiere autenticación
            )
            .formLogin(form -> form
                .loginPage("/login") // Ruta de tu página de login personalizada
                .defaultSuccessUrl("/dashboard_malla", true) // Redirige aquí después de login exitoso
                .failureUrl("/login?error=true") // Redirige aquí si falla el login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true") // Redirige aquí después de cerrar sesión
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/403") // Página para acceso denegado
            );
        return http.build();
    }
}