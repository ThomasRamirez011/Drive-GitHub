package com.gesgan.aplicacion_web_gesgan.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gesgan.aplicacion_web_gesgan.model.Sanidad;
import com.gesgan.aplicacion_web_gesgan.repository.SanidadRepository;

@Service
@Transactional
public class SanidadService {
    
    private final SanidadRepository sanidadRepository;

    public SanidadService(SanidadRepository sanidadRepository) {
        this.sanidadRepository = sanidadRepository;
    }

    public List<Sanidad> listarRegistros() {
        return sanidadRepository.findAll();
    }

    public Optional<Sanidad> buscarPorId(Long id) {
        return sanidadRepository.findById(id);
    }

    public Sanidad guardarRegistro(Sanidad sanidad) {
        return sanidadRepository.save(sanidad);
    }

    public void eliminarRegistro(Long id) {
        sanidadRepository.deleteById(id);
    }

    // Nuevos métodos específicos
    public List<Sanidad> buscarPorTipo(String tipo) {
        return sanidadRepository.findByTipo(tipo);
    }

    public List<Sanidad> buscarPorFecha(LocalDate fecha) {
        return sanidadRepository.findByFecha(fecha);
    }

    public long contarPorTipo(String tipo) {
        return sanidadRepository.countByTipo(tipo);
    }
}