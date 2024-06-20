
package com.example.SSjApi.dto;

import java.sql.Timestamp;

public class PedidosPorUsuarioDTO {
    private String nombreProducto;
    private Timestamp fechaIngreso;
    private Timestamp fechaCreacion;
    private Integer cantidad;
    private String proveedor;
    private Double precioUnitario;
    private String nombreUsuario;

    public PedidosPorUsuarioDTO(String nombreProducto, Timestamp fechaIngreso, Timestamp fechaCreacion,
            Integer cantidad, String proveedor, Double precioUnitario, String nombreUsuario) {
        this.nombreProducto = nombreProducto;
        this.fechaIngreso = fechaIngreso;
        this.fechaCreacion = fechaCreacion;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.precioUnitario = precioUnitario;
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
