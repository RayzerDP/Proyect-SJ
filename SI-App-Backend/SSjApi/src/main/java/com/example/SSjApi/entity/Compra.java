package com.example.SSjApi.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    private String producto;
    private Date fecha;
    private Double compraTotal;

    // Getters y Setters
    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCompraTotal() {
        return compraTotal;
    }

    public void setCompraTotal(Double compraTotal) {
        this.compraTotal = compraTotal;
    }
}
