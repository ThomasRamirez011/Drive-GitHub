package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gesgan.aplicacion_web_gesgan.repository.SanidadRepository;

@Controller
public class SanidadController {

    private final SanidadRepository sanidadRepository;

    public SanidadController(SanidadRepository sanidadRepository) {
        this.sanidadRepository = sanidadRepository;
    }

    @GetMapping("/sanidad")
    public String mostrarSanidad(Model model) {
        model.addAttribute("registros", sanidadRepository.findAll());
        return "sanidad";
    }
}
