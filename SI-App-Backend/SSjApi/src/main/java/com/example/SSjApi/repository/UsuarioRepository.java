package com.example.SSjApi.repository;

import com.example.SSjApi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
}
