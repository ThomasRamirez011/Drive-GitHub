package com.gesgan.aplicacion_web_gesgan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;
import com.gesgan.aplicacion_web_gesgan.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario encriptando la contraseña
     */
    public Usuario registrarUsuario(Usuario usuario) {
        // Validar que no exista el usuario
        if (existePorUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        
        if (existePorCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        
        // Encriptar la contraseña antes de guardar
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
        
        // Establecer valores por defecto si no vienen
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("USER");
        }
        if (usuario.getActivo() == null) {
            usuario.setActivo(true);
        }
        
        return usuarioRepository.save(usuario);
    }

    /**
     * Autentica un usuario (para login personalizado si se necesita)
     */
    public boolean autenticarUsuario(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return passwordEncoder.matches(password, usuario.getPassword()) && 
                   Boolean.TRUE.equals(usuario.getActivo());
        }
        return false;
    }

    /**
     * Obtiene todos los usuarios (solo para administración)
     */
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Elimina un usuario por ID
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Obtiene un usuario por ID
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Busca un usuario por username
     */
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Verifica si existe un usuario por username
     */
    public boolean existePorUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    /**
     * Verifica si existe un usuario por correo
     */
    public boolean existePorCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
    
    /**
     * Busca un usuario por correo
     */
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    /**
     * Actualiza la información de un usuario
     */
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                if (usuarioActualizado.getNombre() != null) {
                    usuario.setNombre(usuarioActualizado.getNombre());
                }
                if (usuarioActualizado.getCorreo() != null && 
                    !usuarioActualizado.getCorreo().equals(usuario.getCorreo())) {
                    if (existePorCorreo(usuarioActualizado.getCorreo())) {
                        throw new RuntimeException("El correo ya está en uso");
                    }
                    usuario.setCorreo(usuarioActualizado.getCorreo());
                }
                return usuarioRepository.save(usuario);
            })
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}