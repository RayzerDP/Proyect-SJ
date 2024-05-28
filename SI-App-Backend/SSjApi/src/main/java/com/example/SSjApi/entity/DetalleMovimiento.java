package com.example.SSjApi.entity;

import jakarta.persistence.*;

@Entity
public class DetalleMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_movimiento")
    private MovimientoInventario movimiento;

    @ManyToOne
    @JoinColumn(name = "id_recurso")
    private Recurso recurso;

    private Integer cantidad;

    // Getters y Setters
    public Integer getIdDetalleMovimiento() {
        return idDetalleMovimiento;
    }

    public void setIdDetalleMovimiento(Integer idDetalleMovimiento) {
        this.idDetalleMovimiento = idDetalleMovimiento;
    }

    public MovimientoInventario getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoInventario movimiento) {
        this.movimiento = movimiento;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
