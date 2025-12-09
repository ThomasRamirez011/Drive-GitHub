package com.gesgan.aplicacion_web_gesgan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesgan.aplicacion_web_gesgan.model.Ganado;

@Repository
public interface GanadoRepository extends JpaRepository<Ganado, String> {

    List<Ganado> findByNumeroPielContainingIgnoreCase(String numeroPiel);
    
    List<Ganado> findByFechaIngresoAfter(java.time.LocalDate fecha);
    List<Ganado> findByFechaIngresoBefore(java.time.LocalDate fecha);
    List<Ganado> findByPesoGreaterThan(Double peso);

}