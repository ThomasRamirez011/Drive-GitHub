package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("titulo", "Panel Principal - Gestión Ganadera");
        return "dashboard"; // templates/dashboard.html
    }
}
