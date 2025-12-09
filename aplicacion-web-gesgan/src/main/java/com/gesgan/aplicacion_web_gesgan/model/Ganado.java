package com.gesgan.aplicacion_web_gesgan.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ganado")
public class Ganado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_piel", nullable = false, length = 100)
    private String numeroPiel;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    // Constructores
    public Ganado() {}

    public Ganado(String numeroPiel, Double peso, LocalDate fechaIngreso) {
        this.numeroPiel = numeroPiel;
        this.peso = peso;
        this.fechaIngreso = fechaIngreso;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroPiel() { return numeroPiel; }
    public void setNumeroPiel(String numeroPiel) { this.numeroPiel = numeroPiel; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    @Override
    public String toString() {
        return "Ganado{" +
                "id=" + id +
                ", numeroPiel='" + numeroPiel + '\'' +
                ", peso=" + peso +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}