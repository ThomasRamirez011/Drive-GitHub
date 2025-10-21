package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gesgan.aplicacion_web_gesgan.service.GanadoService;

@Controller
public class GanadoController {

    private final GanadoService ganadoService;

    public GanadoController(GanadoService ganadoService) {
        this.ganadoService = ganadoService;
    }

    @GetMapping("/ganado")
    public String mostrarGanado(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("usuarioLogueado", true);
            model.addAttribute("username", auth.getName());
            model.addAttribute("ganados", ganadoService.listarGanado());
            return "ganado";
        } else {
            return "redirect:/login";
        }
    }
}