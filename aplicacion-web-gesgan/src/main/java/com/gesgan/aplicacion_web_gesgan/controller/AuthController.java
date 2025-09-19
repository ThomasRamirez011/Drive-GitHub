package com.gesgan.aplicacion_web_gesgan.controller;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;
import com.gesgan.aplicacion_web_gesgan.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol("USER");
        usuarioService.guardarUsuario(usuario);
        return "redirect:/login?registroExitoso";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}