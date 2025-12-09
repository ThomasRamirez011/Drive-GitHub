package com.gesgan.aplicacion_web_gesgan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesgan.aplicacion_web_gesgan.model.Ganado;
import com.gesgan.aplicacion_web_gesgan.repository.GanadoRepository;

@Service
@Transactional
public class GanadoService {
    
    private final GanadoRepository ganadoRepository;

    public GanadoService(GanadoRepository ganadoRepository) {
        this.ganadoRepository = ganadoRepository;
    }

    public List<Ganado> listarGanado() {
        return ganadoRepository.findAll();
    }

    public Optional<Ganado> buscarPorId(Long id) {
        return ganadoRepository.findById(id);
    }

    public Ganado guardarGanado(Ganado ganado) {
        return ganadoRepository.save(ganado);
    }

    public void eliminarGanado(Long id) {
        ganadoRepository.deleteById(id);
    }

    // Nuevos métodos específicos
    public List<Ganado> buscarPorNumeroPiel(String numeroPiel) {
        return ganadoRepository.findByNumeroPielContainingIgnoreCase(numeroPiel);
    }

    public List<Ganado> buscarPorPesoMayorQue(Double peso) {
        return ganadoRepository.findByPesoGreaterThan(peso);
    }
}