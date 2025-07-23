package com.sgturnos.SGTURNOS.security; // PAQUETE CORRECTO PARA ESTE ARCHIVO

import com.sgturnos.SGTURNOS.model.Usuario; // Importa tu modelo de Usuario
import com.sgturnos.SGTURNOS.repository.UsuarioRepository; // Importa tu repositorio de Usuario
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + email);
        }

        // Asegúrate de que el rol se carga correctamente.
        // Si el rol es nulo, podría haber un problema en tu modelo Usuario o en la base de datos.
        if (usuario.getRol() == null || usuario.getRol().getNombreRol() == null) {
            throw new UsernameNotFoundException("El usuario " + email + " no tiene un rol asignado.");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombreRol());

        return new User(
            usuario.getEmail(),
            usuario.getPassword(),
            usuario.isEnabled(), // account non expired
            true, // credentials non expired
            true, // account non locked
            true, // enabled
            Collections.singletonList(authority)
        );
    }
}