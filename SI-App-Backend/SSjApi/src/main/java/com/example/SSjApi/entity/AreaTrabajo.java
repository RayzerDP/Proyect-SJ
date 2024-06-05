package com.example.SSjApi.entity;

import jakarta.persistence.*;

@Entity
public class AreaTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAreaTrabajo;
    private String nombreArea;
    private String descripcion;

    // Getters y Setters

    public Integer getIdAreaTrabajo() {
        return idAreaTrabajo;
    }

    public void setIdAreaTrabajo(Integer idAreaTrabajo) {
        this.idAreaTrabajo = idAreaTrabajo;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
