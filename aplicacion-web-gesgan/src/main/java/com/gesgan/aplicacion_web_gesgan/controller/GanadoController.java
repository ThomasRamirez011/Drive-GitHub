package com.gesgan.aplicacion_web_gesgan.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gesgan.aplicacion_web_gesgan.model.Ganado;
import com.gesgan.aplicacion_web_gesgan.service.GanadoService;

@Controller
public class GanadoController {

    private final GanadoService ganadoService;

    public GanadoController(GanadoService ganadoService) {
        this.ganadoService = ganadoService;
    }

    @GetMapping("/ganado")
    public String mostrarGanado(
            Model model,
            @RequestParam(required = false) String din,
            @RequestParam(required = false) String numeroPiel,
            @RequestParam(required = false) Double pesoMin,
            @RequestParam(required = false) Double pesoMax,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("usuarioLogueado", true);
            model.addAttribute("username", auth.getName());
            // Añadir valores de filtro al modelo para rellenar el formulario
            model.addAttribute("fDin", din == null ? "" : din);
            model.addAttribute("fNumeroPiel", numeroPiel == null ? "" : numeroPiel);
            model.addAttribute("fPesoMin", pesoMin == null ? "" : pesoMin);
            model.addAttribute("fPesoMax", pesoMax == null ? "" : pesoMax);
            model.addAttribute("fFecha", fecha == null ? "" : fecha);

            // Obtener lista filtrada
            model.addAttribute("ganados", ganadoService.buscarConFiltro(din, numeroPiel, pesoMin, pesoMax, fecha));
            // Logs de eliminaciones
            model.addAttribute("deletionLogs", ganadoService.getDeletionLogs());
            model.addAttribute("ganado", new Ganado());
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

    @PostMapping("/ganado/eliminar/{din}")
    public String eliminarGanado(@PathVariable String din){
        ganadoService.eliminarGanado(din);
        return "redirect:/ganado";
    }
    
}
