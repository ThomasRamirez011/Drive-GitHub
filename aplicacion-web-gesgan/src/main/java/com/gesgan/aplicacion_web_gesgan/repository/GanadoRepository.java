package com.gesgan.aplicacion_web_gesgan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesgan.aplicacion_web_gesgan.model.Ganado;

@Repository
public interface GanadoRepository extends JpaRepository<Ganado, Long> {

    // ✅ MÉTODOS QUE SÍ EXISTEN en tu tabla ganado
    List<Ganado> findByTipo(String tipo);
    List<Ganado> findByRaza(String raza);
    List<Ganado> findByNombreContainingIgnoreCase(String nombre);
    
    // Búsqueda por rango de fechas
    List<Ganado> findByFechaIngresoAfter(java.time.LocalDate fecha);
    List<Ganado> findByFechaIngresoBefore(java.time.LocalDate fecha);
    
    // Contar por tipo
    long countByTipo(String tipo);
}