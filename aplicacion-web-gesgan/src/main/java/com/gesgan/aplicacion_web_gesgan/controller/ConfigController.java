package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;
import com.gesgan.aplicacion_web_gesgan.service.UsuarioService;

@Controller
public class ConfigController {

    private final UsuarioService usuarioService;

    public ConfigController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/config")
    public String config(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            // Obtener información completa del usuario
            Usuario usuario = usuarioService.buscarPorUsername(auth.getName())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            model.addAttribute("usuarioLogueado", true);
            model.addAttribute("username", auth.getName());
            model.addAttribute("usuario", usuario); // Enviamos el objeto usuario completo
            
            return "config";
        } else {
            return "redirect:/login";
        }
    }
}