package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gesgan.aplicacion_web_gesgan.repository.GanadoRepository;

@Controller
public class GanadoController {

    private final GanadoRepository ganadoRepository;

    public GanadoController(GanadoRepository ganadoRepository) {
        this.ganadoRepository = ganadoRepository;
    }

    @GetMapping("/ganado")
    public String mostrarGanado(Model model) {
        model.addAttribute("ganados", ganadoRepository.findAll());
        return "ganado";
    }
}
