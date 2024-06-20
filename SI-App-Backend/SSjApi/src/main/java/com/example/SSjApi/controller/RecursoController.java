package com.example.SSjApi.controller;

import com.example.SSjApi.dto.RecursoPorCategoriaDTO;
import com.example.SSjApi.entity.AreaTrabajo;
import com.example.SSjApi.entity.Categoria;
import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping("/area-trabajo/{idAreaTrabajo}")
    public ResponseEntity<List<Recurso>> getRecursosByAreaTrabajo(@PathVariable Integer idAreaTrabajo) {
        List<Recurso> recursos = recursoService.getRecursosByAreaTrabajo(idAreaTrabajo);
        if (recursos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(recursos);
        }
    }

    @PostMapping
    public ResponseEntity<Recurso> createRecurso(@RequestBody Recurso recurso) {
        Recurso createdRecurso = recursoService.createRecurso(recurso);
        return ResponseEntity.ok(createdRecurso);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = recursoService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/areas-trabajo")
    public ResponseEntity<List<AreaTrabajo>> getAreasTrabajoForUser(@RequestParam Integer userId) {
        List<AreaTrabajo> areasTrabajo = recursoService.getAreasTrabajoForUser(userId);
        return ResponseEntity.ok(areasTrabajo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> updateRecurso(@PathVariable Integer id, @RequestBody Recurso recurso) {
        Recurso updatedRecurso = recursoService.updateRecurso(id, recurso);
        if (updatedRecurso == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedRecurso);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable Integer id) {
        recursoService.deleteRecurso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Recurso>> getAllRecursos() {
        List<Recurso> recursos = recursoService.getAllRecursos();
        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/por-categoria")
    public ResponseEntity<List<RecursoPorCategoriaDTO>> getRecursosPorCategoria() {
        List<RecursoPorCategoriaDTO> recursosPorCategoria = recursoService.getRecursosPorCategoria();
        return ResponseEntity.ok(recursosPorCategoria);
    }
}
