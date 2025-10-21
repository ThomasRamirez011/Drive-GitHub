package com.gesgan.aplicacion_web_gesgan.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;
import com.gesgan.aplicacion_web_gesgan.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar usuario activo por username (usando tu método personalizado)
        Usuario usuario = usuarioRepository.findActiveByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado o inactivo: " + username));

        // Verificar que el usuario esté activo (doble verificación)
        if (!usuario.getActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                true, // enabled - ya verificamos que está activo
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                getAuthorities(usuario)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
        // Convertir el rol del usuario a GrantedAuthority
        // Asegurarse de que el rol tenga el prefijo "ROLE_" que Spring Security espera
        String role = usuario.getRol();
        if (role != null && !role.trim().isEmpty()) {
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }
            return Collections.singletonList(new SimpleGrantedAuthority(role));
        }
        
        // Rol por defecto si no está especificado
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}