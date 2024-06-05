package com.example.SSjApi.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecurso;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private Date fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "idAreaTrabajo")
    private AreaTrabajo areaTrabajo;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    // Getters y setters

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public AreaTrabajo getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(AreaTrabajo areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
