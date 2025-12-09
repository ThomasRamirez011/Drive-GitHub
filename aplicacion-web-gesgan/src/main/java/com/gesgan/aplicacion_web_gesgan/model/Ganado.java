package com.gesgan.aplicacion_web_gesgan.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ganado")
public class Ganado {

    @Id
    @Column(name = "din", nullable = false, length = 100)
    private String din;

    @Column(name = "numero_piel", nullable = false, length = 100)
    private String numeroPiel;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    // Constructores
    public Ganado() {}

    public Ganado(String din, String numeroPiel, Double peso, LocalDate fechaIngreso) {
        this.din = din;
        this.numeroPiel = numeroPiel;
        this.peso = peso;
        this.fechaIngreso = fechaIngreso;
    }

    // Getters y Setters

    public String getDin() { return din; }
    public void setDin(String din) { this.din = din; }

    public String getNumeroPiel() { return numeroPiel; }
    public void setNumeroPiel(String numeroPiel) { this.numeroPiel = numeroPiel; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    @Override
    public String toString() {
        return "Ganado{" +
                ", din='" + din + '\'' +
                ", numeroPiel='" + numeroPiel + '\'' +
                ", peso=" + peso +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ganado ganado = (Ganado) o;
        return Objects.equals(din, ganado.din);
    }

    @Override
    public int hashCode() {
        return Objects.hash(din);
    }
}