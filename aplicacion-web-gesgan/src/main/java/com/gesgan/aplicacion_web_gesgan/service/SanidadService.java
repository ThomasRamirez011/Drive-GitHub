package com.gesgan.aplicacion_web_gesgan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gesgan.aplicacion_web_gesgan.model.Sanidad;
import com.gesgan.aplicacion_web_gesgan.repository.SanidadRepository;

@Service
public class SanidadService {
    private final SanidadRepository sanidadRepository;

    public SanidadService(SanidadRepository sanidadRepository) {
        this.sanidadRepository = sanidadRepository;
    }

    public List<Sanidad> listarRegistros() {
        return sanidadRepository.findAll();
    }
}
