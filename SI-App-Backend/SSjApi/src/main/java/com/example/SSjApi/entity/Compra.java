package com.example.SSjApi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;
    private LocalDateTime fecha;
    private String datosAdicionales;

    // Getters y Setters
    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }
}
