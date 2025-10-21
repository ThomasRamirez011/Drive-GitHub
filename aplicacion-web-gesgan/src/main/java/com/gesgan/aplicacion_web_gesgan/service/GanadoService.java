package com.gesgan.aplicacion_web_gesgan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gesgan.aplicacion_web_gesgan.model.Ganado;
import com.gesgan.aplicacion_web_gesgan.repository.GanadoRepository;

@Service
public class GanadoService {
    private final GanadoRepository ganadoRepository;

    public GanadoService(GanadoRepository ganadoRepository) {
        this.ganadoRepository = ganadoRepository;
    }

    public List<Ganado> listarGanado() {
        return ganadoRepository.findAll();
    }
}
