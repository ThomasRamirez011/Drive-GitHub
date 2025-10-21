package com.gesgan.aplicacion_web_gesgan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;
import com.gesgan.aplicacion_web_gesgan.service.UsuarioService;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String correo) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setCorreo(correo);
        
        usuarioService.registrarUsuario(usuario);
        return "redirect:/login";
    }
}