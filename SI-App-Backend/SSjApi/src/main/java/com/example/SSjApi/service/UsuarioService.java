package com.example.SSjApi.service;

import com.example.SSjApi.entity.Usuario;
import com.example.SSjApi.exception.ResourceNotFoundException;
import com.example.SSjApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario authenticate(String nombreUsuario, String contrasena) {
        // Utilizar el método findByNombreUsuarioAndContrasena del repositorio
        return usuarioRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
    }

    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
    }

    public long countUsuarios() {
        return usuarioRepository.count();
    }
}
