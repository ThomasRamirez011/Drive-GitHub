package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gesgan.aplicacion_web_gesgan.service.SanidadService;

@Controller
public class SanidadController {

    private final SanidadService sanidadService;

    public SanidadController(SanidadService sanidadService) {
        this.sanidadService = sanidadService;
    }

    @GetMapping("/sanidad")
    public String mostrarSanidad(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("usuarioLogueado", true);
            model.addAttribute("username", auth.getName());
            model.addAttribute("registros", sanidadService.listarRegistros());
            return "sanidad";
        } else {
            return "redirect:/login";
        }
    }
}