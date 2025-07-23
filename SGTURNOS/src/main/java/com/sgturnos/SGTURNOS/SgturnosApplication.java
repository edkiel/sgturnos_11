package com.sgturnos.SGTURNOS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgturnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgturnosApplication.class, args);
	}

}


/*  EXTRACTO DE CODIGO PARA GENERAR CIFRADO DE CONTRASEÑAS PARA EL PRIMER INICIO DE SESION 
package com.sgturnos.SGTURNOS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; // Asegúrate de importar esto

@SpringBootApplication
public class SgturnosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgturnosApplication.class, args);

        // --- CÓDIGO TEMPORAL PARA GENERAR CONTRASEÑAS ---
        // Puedes ejecutar esto una vez para obtener las contraseñas cifradas
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPasswordAdmin = "admin123"; // Contraseña sin cifrar
        String encodedPasswordAdmin = passwordEncoder.encode(rawPasswordAdmin);
        System.out.println("Contraseña cifrada para '" + rawPasswordAdmin + "': " + encodedPasswordAdmin);

        String rawPasswordJefe = "jefe123"; // Contraseña sin cifrar
        String encodedPasswordJefe = passwordEncoder.encode(rawPasswordJefe);
        System.out.println("Contraseña cifrada para '" + rawPasswordJefe + "': " + encodedPasswordJefe);

        String rawPasswordEmpleado = "empleado123"; // Contraseña sin cifrar
        String encodedPasswordEmpleado = passwordEncoder.encode(rawPasswordEmpleado);
        System.out.println("Contraseña cifrada para '" + rawPasswordEmpleado + "': " + encodedPasswordEmpleado);
        // --------------------------------------------------
    }

    // Ya tienes este Bean en SecurityConfig, no es necesario aquí si ya está allí
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
}
*/




