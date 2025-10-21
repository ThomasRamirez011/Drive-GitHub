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

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "raza", length = 50)
    private String raza;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    // Constructores
    public Ganado() {}

    public Ganado(String nombre, String tipo, String raza, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaIngreso = fechaIngreso;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    @Override
    public String toString() {
        return "Ganado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", raza='" + raza + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}