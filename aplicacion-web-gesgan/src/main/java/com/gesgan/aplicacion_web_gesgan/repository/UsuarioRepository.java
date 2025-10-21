package com.gesgan.aplicacion_web_gesgan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesgan.aplicacion_web_gesgan.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Búsqueda por username
    Optional<Usuario> findByUsername(String username);
    
    // Búsqueda por correo
    Optional<Usuario> findByCorreo(String correo);
    
    // Verificación de existencia
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
    
    // Consulta personalizada para usuario activo por username
    @Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.activo = true")
    Optional<Usuario> findActiveByUsername(@Param("username") String username);
    
    // Consulta personalizada para usuario activo por correo
    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.activo = true")
    Optional<Usuario> findActiveByCorreo(@Param("correo") String correo);
    
    // Búsqueda por rol
    List<Usuario> findByRol(String rol);
    
    // Búsqueda de usuarios activos
    List<Usuario> findByActivoTrue();
    
    // Búsqueda de usuarios inactivos
    List<Usuario> findByActivoFalse();
    
    // Búsqueda por nombre (like)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Usuario> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
    
    // Contar usuarios por rol
    long countByRol(String rol);
    
    // Contar usuarios activos
    long countByActivoTrue();
    
    // Verificar si existe por username ignorando mayúsculas/minúsculas
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE LOWER(u.username) = LOWER(:username)")
    boolean existsByUsernameIgnoreCase(@Param("username") String username);
    
    // Verificar si existe por correo ignorando mayúsculas/minúsculas
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE LOWER(u.correo) = LOWER(:correo)")
    boolean existsByCorreoIgnoreCase(@Param("correo") String correo);
}