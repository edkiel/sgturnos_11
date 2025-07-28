package com.sgturnos.SGTURNOS.controller;

import com.sgturnos.SGTURNOS.model.Usuario;
import com.sgturnos.SGTURNOS.model.Empleado; // ¡Asegúrate de que este import esté presente!
import com.sgturnos.SGTURNOS.model.Rol; // Importar la clase Rol
import com.sgturnos.SGTURNOS.repository.UsuarioRepository;
import com.sgturnos.SGTURNOS.repository.EmpleadoRepository; // ¡Asegúrate de que este import esté presente!
import com.sgturnos.SGTURNOS.repository.RolRepository; // Importar RolRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Importar PasswordEncoder
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Para manejar errores de validación
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // Para vincular el objeto al formulario
import org.springframework.web.bind.annotation.PathVariable; // Para capturar el ID de la URL
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Para mensajes flash
import org.springframework.transaction.annotation.Transactional; // ¡NUEVO IMPORT

import jakarta.validation.Valid; // Para validaciones, asegúrate de tener la dependencia en pom.xml
import java.util.Optional; // Para manejar el resultado de findById

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Edkiel
 * para manejar las peticiones HTTP de los mismos usuarios a la BD.
 */
@Controller
@RequestMapping("/admin") // Todas las rutas en este controlador comenzarán con /admin
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class); // Declarar logger
    private final RolRepository rolRepository; // Necesitamos el repositorio de roles
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Necesitamos el PasswordEncoder
    private final EmpleadoRepository empleadoRepository; // ¡Declaración de EmpleadoRepository!

    @Autowired // Spring inyectará los repositorios y el PasswordEncoder aquí
    public UsuarioController(UsuarioRepository usuarioRepository, RolRepository rolRepository, EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.empleadoRepository = empleadoRepository; // ¡AHORA SÍ, 'empleadoRepository' ES UN PARÁMETRO Y SE ASIGNA!
        this.passwordEncoder = passwordEncoder;
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
    // --- MÉTODOS PARA CREAR USUARIO ---

    // Método para mostrar el formulario de creación de usuario
    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("usuario", new Usuario()); // Pasa un objeto Usuario vacío al formulario
        model.addAttribute("roles", rolRepository.findAll()); // Pasa todos los roles disponibles
        // Puedes añadir también empleados si quieres un select para vincular en la creación
        // model.addAttribute("empleados", empleadoRepository.findAll()); 
        return "usuarios/crear"; // Nombre de la plantilla HTML
    }

    // Método para procesar el envío del formulario de creación
    @Transactional // ¡AÑADIR ESTA ANOTACIÓN!
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") @Valid Usuario usuario,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", rolRepository.findAll());
            // Si tuvieras un select de empleados en el form de crear, también deberías volver a cargarlos aquí
            // model.addAttribute("empleados", empleadoRepository.findAll());
            return "usuarios/crear";
        }

        // --- ESTE ES EL CAMBIO CRÍTICO PARA EL ERROR DE EMPLEADO EN GUARDAR ---
        // Si el objeto Empleado existe pero su ID es nulo, o si el ID es 0 (valor por defecto para int/Integer en formularios vacíos)
        // entonces no intentes asociar un Empleado. Establece la referencia a null.
        if (usuario.getEmpleado() != null &&
            (usuario.getEmpleado().getIdEmpleado() == null || usuario.getEmpleado().getIdEmpleado() == 0)) {
            usuario.setEmpleado(null); // Desvincula el Empleado si no se proporcionó uno válido o se dejó vacío
        } else if (usuario.getEmpleado() != null && usuario.getEmpleado().getIdEmpleado() != null) {
            // Si se proporcionó un ID de empleado, debemos cargar la instancia gestionada
            Optional<Empleado> empleadoOpt = empleadoRepository.findById(usuario.getEmpleado().getIdEmpleado());
            if (empleadoOpt.isPresent()) {
                usuario.setEmpleado(empleadoOpt.get());
            } else {
                // Si el ID de Empleado proporcionado no existe
                redirectAttributes.addFlashAttribute("mensaje", "El ID de Empleado proporcionado no existe. Por favor, ingrese un ID de empleado válido.");
                redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
                model.addAttribute("roles", rolRepository.findAll());
                return "usuarios/crear"; // Volver al formulario de creación
            }
        }
        // --- FIN DEL CAMBIO ---

        // Antes de guardar, cifrar la contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asegurarse de que el usuario esté habilitado por defecto al crear
        usuario.setEnabled(true);

        // Guardar el usuario en la base de datos
        usuarioRepository.save(usuario);

        redirectAttributes.addFlashAttribute("mensaje", "Usuario creado exitosamente!");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");

        return "redirect:/admin/usuarios"; // Redirigir a la lista de usuarios
    }

    // --- MÉTODOS PARA EDITAR USUARIO ---

    // Método para mostrar el formulario de edición de usuario
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensaje", "Usuario no encontrado.");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/admin/usuarios";
        }

        model.addAttribute("usuario", usuarioOptional.get()); // Pasa el usuario encontrado al formulario
        model.addAttribute("roles", rolRepository.findAll()); // Pasa todos los roles disponibles
        return "usuarios/editar"; // Nombre de la plantilla HTML para edición
    }

    // Método para procesar el envío del formulario de edición
    @Transactional // ¡AÑADIR ESTA ANOTACIÓN!
    @PostMapping("/usuarios/actualizar/{id}")
    public String actualizarUsuario(@PathVariable("id") Integer id,
                                    @ModelAttribute("usuario") @Valid Usuario usuario,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {

        logger.info("Iniciando actualización de usuario con ID: {}", id);
        logger.info("Usuario recibido del formulario (parcial): Email={}, Empleado ID={}",
                    usuario.getEmail(),
                    (usuario.getEmpleado() != null && usuario.getEmpleado().getIdEmpleado() != null ? usuario.getEmpleado().getIdEmpleado() : "NULL"));

        if (result.hasErrors()) {
            logger.warn("Errores de validación en el formulario de actualización.");
            model.addAttribute("roles", rolRepository.findAll());
            return "usuarios/editar";
        }

        Optional<Usuario> existingUserOptional = usuarioRepository.findById(id);
        if (existingUserOptional.isEmpty()) {
            logger.error("Usuario con ID {} no encontrado para actualizar.", id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario no encontrado para actualizar.");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/admin/usuarios";
        }

        Usuario existingUser = existingUserOptional.get();
        logger.info("Usuario existente cargado de la DB: ID={}, Email={}, Empleado ID={}",
                    existingUser.getIdUsuario(), existingUser.getEmail(),
                    (existingUser.getEmpleado() != null ? existingUser.getEmpleado().getIdEmpleado() : "NULL"));


        existingUser.setEmail(usuario.getEmail());
        
        // Lógica de actualización de Rol
        if (usuario.getRol() != null && usuario.getRol().getIdRol() != null) {
             Optional<Rol> rolOpt = rolRepository.findById(usuario.getRol().getIdRol());
             if (rolOpt.isPresent()) {
                 existingUser.setRol(rolOpt.get());
             } else {
                 logger.warn("El rol seleccionado con ID {} no es válido.", usuario.getRol().getIdRol());
                 redirectAttributes.addFlashAttribute("mensaje", "El rol seleccionado no es válido.");
                 redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
                 return "redirect:/admin/usuarios"; 
             }
        } else {
             logger.debug("No se proporcionó un Rol para la actualización.");
        }

        // --- BLOQUE CLAVE PARA EMPLEADO (CORREGIDO Y REFORZADO) ---
        if (usuario.getEmpleado() != null && usuario.getEmpleado().getIdEmpleado() != null) {
            Integer idEmpleadoForm = usuario.getEmpleado().getIdEmpleado();
            logger.info("ID de Empleado recibido del formulario para actualizar: {}", idEmpleadoForm);

            // Si el ID es 0, trátalo como si estuviera vacío (campo numérico vacío a veces se parsea a 0)
            if (idEmpleadoForm != 0) {
                Optional<Empleado> empleadoOpt = empleadoRepository.findById(idEmpleadoForm);
                if (empleadoOpt.isPresent()) {
                    existingUser.setEmpleado(empleadoOpt.get()); // Asigna la instancia de Empleado cargada
                    logger.info("Empleado con ID {} cargado exitosamente de la DB y asignado.", idEmpleadoForm);
                } else {
                    logger.warn("ID de Empleado {} no encontrado en la base de datos.", idEmpleadoForm);
                    redirectAttributes.addFlashAttribute("mensaje", "El ID de Empleado proporcionado no existe. Por favor, ingrese un ID de empleado válido.");
                    redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
                    
                    model.addAttribute("usuario", usuario); 
                    model.addAttribute("roles", rolRepository.findAll());
                    // Puedes volver a cargar los empleados aquí si tuvieras un select en el form de edición
                    // model.addAttribute("empleados", empleadoRepository.findAll());
                    return "usuarios/editar"; 
                }
            } else { 
                 existingUser.setEmpleado(null); // Si el ID es 0, desvincula el empleado
                 logger.info("ID de Empleado recibido es 0, estableciendo Empleado a NULL.");
            }
        } else {
            // Si el campo de ID Empleado se dejó vacío en el formulario (o el objeto Empleado era null)
            existingUser.setEmpleado(null);
            logger.info("Campo de ID Empleado vacío o nulo en el formulario, estableciendo Empleado a NULL.");
        }
        // --- FIN DEL BLOQUE EMPLEADO ---

        // Actualizar el estado de enabled
        existingUser.setEnabled(usuario.isEnabled());

        // Actualizar la contraseña si se proporciona una nueva
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(usuario.getPassword()));
            logger.info("Contraseña actualizada para usuario con ID: {}", existingUser.getIdUsuario());
        } else {
            logger.info("Contraseña no modificada para usuario con ID: {}", existingUser.getIdUsuario());
        }

        usuarioRepository.save(existingUser);
        logger.info("Usuario con ID {} actualizado y guardado exitosamente.", existingUser.getIdUsuario());

        redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente!");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");

        return "redirect:/admin/usuarios";
    }
}