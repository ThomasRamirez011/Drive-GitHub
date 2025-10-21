package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;
import com.gesgan.aplicacion_web_gesgan.service.UsuarioService;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            // Validaciones básicas
            if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
                model.addAttribute("error", "El nombre de usuario es requerido");
                return "registro";
            }
            
            if (usuario.getPassword() == null || usuario.getPassword().length() < 4) {
                model.addAttribute("error", "La contraseña debe tener al menos 4 caracteres");
                return "registro";
            }
            
            if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
                model.addAttribute("error", "El correo electrónico es requerido");
                return "registro";
            }
            
            // Registrar el usuario
            usuarioService.registrarUsuario(usuario);
            model.addAttribute("exito", "¡Registro exitoso! Ahora puedes iniciar sesión.");
            return "login";
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            model.addAttribute("usuario", usuario); // Mantener datos en el formulario
            return "registro";
        }
    }
}