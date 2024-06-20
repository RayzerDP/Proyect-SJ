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
    private String observaciones; // Nuevo campo
    private String proveedor; // Nuevo campo
    private Integer cantidadMinima;

    @ManyToOne
    @JoinColumn(name = "idAreaTrabajo")
    private AreaTrabajo areaTrabajo;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

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

    public String getObservaciones() { // Nuevo método getter
        return observaciones;
    }

    public void setObservaciones(String observaciones) { // Nuevo método setter
        this.observaciones = observaciones;
    }

    public String getProveedor() { // Nuevo método getter
        return proveedor;
    }

    public void setProveedor(String proveedor) { // Nuevo método setter
        this.proveedor = proveedor;
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

    public Integer getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(Integer cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public Usuario getUsuario() { // Añade este getter
        return usuario;
    }

    public void setUsuario(Usuario usuario) { // Añade este setter
        this.usuario = usuario;
    }
}
