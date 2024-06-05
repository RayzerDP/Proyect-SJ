package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.entity.Usuario;
import com.example.SSjApi.repository.RecursoRepository;
import com.example.SSjApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecursoRepository recursoRepository; // Inyectar RecursoRepository

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
            usuario.setTipoUsuario(usuarioDetails.getTipoUsuario());
            usuario.setContrasena(usuarioDetails.getContrasena());
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setRut(usuarioDetails.getRut());
            usuario.setAreaTrabajo(usuarioDetails.getAreaTrabajo());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        String nombreUsuario = loginData.get("nombreUsuario");
        String contrasena = loginData.get("contrasena");

        Usuario usuario = usuarioRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        Map<String, String> response = new HashMap<>();

        if (usuario != null) {
            response.put("message", "Login successful");
            response.put("token", "dummy-token"); // Aquí deberías generar un token real
            response.put("userId", usuario.getIdUsuario().toString()); // Devolver el userId
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/{id}/recursos")
    public ResponseEntity<List<Recurso>> getRecursosByUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Recurso> recursos = recursoRepository
                .findByAreaTrabajo_IdAreaTrabajo(usuario.getAreaTrabajo().getIdAreaTrabajo());
        return ResponseEntity.ok(recursos);
    }
}
