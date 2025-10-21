package com.gesgan.aplicacion_web_gesgan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesgan.aplicacion_web_gesgan.model.Sanidad;

@Repository
public interface SanidadRepository extends JpaRepository<Sanidad, Long> {

    // ✅ MÉTODOS QUE SÍ EXISTEN en tu tabla sanidad
    List<Sanidad> findByTipo(String tipo);
    List<Sanidad> findByFecha(LocalDate fecha);
    
    // Búsqueda por rango de fechas
    List<Sanidad> findByFechaAfter(LocalDate fecha);
    List<Sanidad> findByFechaBefore(LocalDate fecha);
    
    // Búsqueda por descripción (like)
    List<Sanidad> findByDescripcionContainingIgnoreCase(String descripcion);
    
    // Contar por tipo
    long countByTipo(String tipo);
}