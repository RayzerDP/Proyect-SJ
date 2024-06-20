package com.example.SSjApi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nombreUsuario;
    private String tipoUsuario;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String email;
    private String rut;
    private String avatar_url; // Añadir este campo

    @ManyToOne
    @JoinColumn(name = "idAreaTrabajo")
    private AreaTrabajo areaTrabajo;

    @ManyToMany
    @JoinTable(name = "usuario_categoria", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idCategoria"))
    private List<Categoria> categorias;

    // Este campo es necesario para que Jackson pueda deserializar el JSON
    @Transient
    private Integer idAreaTrabajo;

    // Getters y setters

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getAvatar_Url() {
        return avatar_url;
    }

    public void setAvatar_Url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public AreaTrabajo getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(AreaTrabajo areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Integer getIdAreaTrabajo() {
        return idAreaTrabajo;
    }

    @JsonProperty("idAreaTrabajo")
    public void setIdAreaTrabajo(Integer idAreaTrabajo) {
        this.idAreaTrabajo = idAreaTrabajo;
    }
}
