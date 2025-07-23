package com.sgturnos.SGTURNOS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        // Busca src/main/resources/templates/login/login.html
        return "login/login"; 
    }

    @GetMapping("/")
    public String redirectToLogin() {
        // Redirige la raíz a la página de login
        return "redirect:/login"; 
    }

    @GetMapping("/dashboard_malla")
    public String showDashboard() {
        // Busca src/main/resources/templates/mallas/dashboard_malla.html
        return "mallas/dashboard_malla"; 
    }

    @GetMapping("/403")
    public String accessDenied() {
        // Para el error 403, podemos ponerlo en la raíz de templates o en una carpeta común
        // Por simplicidad, lo dejaremos por ahora en templates/403.html
        // Si quieres moverlo a una carpeta como 'errores', tendrías que cambiarlo aquí.
        return "403"; 
    }
}