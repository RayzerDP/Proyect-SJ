package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.repository.CategoriaRepository;
import com.example.SSjApi.repository.RecursoRepository;
import com.example.SSjApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/graficos")
public class GraficoController {

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/recursos-por-categoria")
    public Map<String, Long> getRecursosPorCategoria(@RequestParam Integer userId) {
        List<Recurso> recursos = recursoRepository.findByAreaTrabajo_IdAreaTrabajo(
                usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                        .getAreaTrabajo().getIdAreaTrabajo());
        return recursos.stream()
                .collect(Collectors.groupingBy(recurso -> recurso.getCategoria().getDescripcion(),
                        Collectors.counting()));
    }
}
