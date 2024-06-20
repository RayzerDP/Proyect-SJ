package com.example.SSjApi.controller;

import com.example.SSjApi.entity.AreaTrabajo;
import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.entity.Usuario;
import com.example.SSjApi.exception.ResourceNotFoundException;
import com.example.SSjApi.repository.AreaTrabajoRepository;
import com.example.SSjApi.repository.RecursoRepository;
import com.example.SSjApi.repository.UsuarioRepository;
import com.example.SSjApi.service.UsuarioService;

import org.springframework.web.multipart.MultipartFile;
import com.example.SSjApi.util.FileUploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AreaTrabajoRepository areaTrabajoRepository;

    @Autowired
    private RecursoRepository recursoRepository; // Inyectar RecursoRepository

    @Autowired
    private UsuarioService usuarioService; // Inyectar UsuarioService

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        // Guardar el archivo en el servidor
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "user-photos/";
        try {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            Map<String, String> response = new HashMap<>();
            response.put("url", uploadDir + fileName);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestParam("file") MultipartFile file,
            @RequestParam("usuario") String usuarioString) {
        try {
            // Convertir el string JSON a un objeto Usuario
            ObjectMapper objectMapper = new ObjectMapper();
            Usuario usuario = objectMapper.readValue(usuarioString, Usuario.class);

            // Manejar la carga del archivo
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "user-photos/";

            FileUploadUtil.saveFile(uploadDir, fileName, file);
            usuario.setAvatar_Url(uploadDir + fileName);

            // Guardar el usuario en la base de datos
            Usuario savedUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.ok(savedUsuario);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestParam("usuario") String usuarioStr,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Usuario usuarioDetails = new ObjectMapper().readValue(usuarioStr, Usuario.class);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found for this id :: " + id));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellido(usuarioDetails.getApellido());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setRut(usuarioDetails.getRut());
        usuario.setNombreUsuario(usuarioDetails.getNombreUsuario());
        usuario.setTipoUsuario(usuarioDetails.getTipoUsuario());

        // Aquí asignamos el AreaTrabajo correspondiente basado en idAreaTrabajo
        if (usuarioDetails.getIdAreaTrabajo() != null) {
            AreaTrabajo areaTrabajo = areaTrabajoRepository.findById(usuarioDetails.getIdAreaTrabajo())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "AreaTrabajo not found for this id :: " + usuarioDetails.getIdAreaTrabajo()));
            usuario.setAreaTrabajo(areaTrabajo);
        }

        if (usuarioDetails.getContrasena() != null && !usuarioDetails.getContrasena().isEmpty()) {
            usuario.setContrasena(usuarioDetails.getContrasena());
        }

        if (file != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            usuario.setAvatar_Url("user-photos/" + fileName);
            FileUploadUtil.saveFile("user-photos", fileName, file);
        }

        final Usuario updatedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> userCredentials) {
        String nombreUsuario = userCredentials.get("nombreUsuario");
        String contrasena = userCredentials.get("contrasena");

        // Lógica para autenticar al usuario
        Usuario usuario = usuarioService.authenticate(nombreUsuario, contrasena);
        if (usuario != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", "dummy-token"); // Aquí va el token real
            response.put("userId", usuario.getIdUsuario());
            response.put("tipoUsuario", usuario.getTipoUsuario());
            response.put("idAreaTrabajo", usuario.getAreaTrabajo().getIdAreaTrabajo());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Login failed"));
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
