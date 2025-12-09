package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gesgan.aplicacion_web_gesgan.model.Ganado;
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
            model.addAttribute("ganado", new Ganado()); // Para el formulario nuevo
            return "ganado";
        } else {
            return "redirect:/login";
        }
    }


    @PostMapping("/ganado/guardar")
    public String guardarGanado(@ModelAttribute Ganado ganado) {
        ganadoService.guardarGanado(ganado);
        return "redirect:/ganado";
    }

    @PostMapping("/ganado/eliminar/{id}")
    public String eliminarGanado(@PathVariable("id") Long id) {
        ganadoService.eliminarGanado(id);
        return "redirect:/ganado";
    }
    
}