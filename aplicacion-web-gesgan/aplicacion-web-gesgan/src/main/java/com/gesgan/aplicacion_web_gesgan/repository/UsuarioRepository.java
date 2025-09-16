package com.gesgan.aplicacion_web_gesgan.repository;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);
    
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    
    boolean existsByEmail(String email);
    
    List<Usuario> findByApellidoContainingIgnoreCase(String apellido);
}

