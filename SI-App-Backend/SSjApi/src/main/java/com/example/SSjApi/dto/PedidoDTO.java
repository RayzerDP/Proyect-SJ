package com.example.SSjApi.dto;

import java.util.Date;

public class PedidoDTO {
    private int idPedido;
    private Date fechaCreacion;
    private String nombreProducto;
    private String descripcion;
    private Date fechaIngreso;
    private int cantidad;
    private String proveedor;
    private String observaciones;
    private double precioUnitario;
    private String nombreUsuario;
    private String estado; // Nuevo campo

    // Constructor completo
    public PedidoDTO(int idPedido, Date fechaCreacion, String nombreProducto, String descripcion, Date fechaIngreso,
            int cantidad, String proveedor, String observaciones, double precioUnitario, String nombreUsuario,
            String estado) {
        this.idPedido = idPedido;
        this.fechaCreacion = fechaCreacion;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.observaciones = observaciones;
        this.precioUnitario = precioUnitario;
        this.nombreUsuario = nombreUsuario;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
