package com.sgturnos.SGTURNOS.controller;

import com.sgturnos.SGTURNOS.model.Usuario; // Asegúrate de que tu clase Usuario esté en este paquete
import com.sgturnos.SGTURNOS.repository.UsuarioRepository; // Asegúrate de que tu UsuarioRepository esté en este paquete
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Para pasar datos a la vista
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Edkiel
 * para manejar las peticiones HTTP  de los mismo susaurios a la BD.
 */
@Controller
@RequestMapping("/admin") // Todas las rutas en este controlador comenzarán con /admin
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired // Spring inyectará el UsuarioRepository aquí
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para mostrar la lista de usuarios
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        // Buscar todos los usuarios en la base de datos
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        // Añadir la lista de usuarios al modelo para que sea accesible en la vista
        model.addAttribute("usuarios", usuarios);
        // Retornar el nombre de la plantilla HTML (relativo a src/main/resources/templates/)
        // Como tu carpeta de usuarios está en 'templates/usuarios/', la ruta es 'usuarios/lista'
        return "usuarios/lista";
    }

    // Aquí irán otros métodos para crear, editar, eliminar...
}