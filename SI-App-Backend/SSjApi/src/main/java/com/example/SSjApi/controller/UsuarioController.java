package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Usuario;
import com.example.SSjApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombreUsuario(usuarioDetails.getNombreUsuario());
            usuario.setContrasena(usuarioDetails.getContrasena());
            usuario.setNombreCompleto(usuarioDetails.getNombreCompleto());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setRut(usuarioDetails.getRut());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}
