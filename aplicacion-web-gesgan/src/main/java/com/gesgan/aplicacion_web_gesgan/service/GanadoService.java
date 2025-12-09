package com.gesgan.aplicacion_web_gesgan.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesgan.aplicacion_web_gesgan.model.DeletionLogEntry;
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

    public Optional<Ganado> buscarPorId(String din) {
        return ganadoRepository.findById(din);
    }

    public Ganado guardarGanado(Ganado ganado) {
        return ganadoRepository.save(ganado);
    }

    public void eliminarGanado(String din) {
        // Registrar la eliminación con timestamp antes de borrar
        if (din != null) {
            DeletionLogEntry entry = new DeletionLogEntry(din, LocalDateTime.now());
            deletionLogs.add(entry);
        }
        ganadoRepository.deleteById(din);
    }

    // Nuevos métodos específicos
    public List<Ganado> buscarPorNumeroPiel(String numeroPiel) {
        return ganadoRepository.findByNumeroPielContainingIgnoreCase(numeroPiel);
    }

    public List<Ganado> buscarPorPesoMayorQue(Double peso) {
        return ganadoRepository.findByPesoGreaterThan(peso);
    }
    // Lista segura para registrar eliminaciones
    private final CopyOnWriteArrayList<DeletionLogEntry> deletionLogs = new CopyOnWriteArrayList<>();

    public List<DeletionLogEntry> getDeletionLogs() {
        return deletionLogs;
    }
    // Búsqueda combinada por filtros opcionales
    public List<Ganado> buscarConFiltro(String din, String numeroPiel, Double pesoMin, Double pesoMax, java.time.LocalDate fecha) {
        return listarGanado().stream()
                .filter(g -> {
                    if (din != null && !din.isEmpty()) {
                        if (g.getDin() == null || !g.getDin().contains(din)) return false;
                    }
                    if (numeroPiel != null && !numeroPiel.isEmpty()) {
                        if (g.getNumeroPiel() == null || !g.getNumeroPiel().toLowerCase().contains(numeroPiel.toLowerCase())) return false;
                    }
                    if (pesoMin != null) {
                        if (g.getPeso() == null || g.getPeso() < pesoMin) return false;
                    }
                    if (pesoMax != null) {
                        if (g.getPeso() == null || g.getPeso() > pesoMax) return false;
                    }
                    if (fecha != null) {
                        if (g.getFechaIngreso() == null || !g.getFechaIngreso().isEqual(fecha)) return false;
                    }
                    return true;
                })
                .toList();
    }

}