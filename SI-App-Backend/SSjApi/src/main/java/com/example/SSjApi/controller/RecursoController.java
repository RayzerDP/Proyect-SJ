package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {
    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping
    public List<Recurso> getAllRecursos() {
        return recursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Recurso getRecursoById(@PathVariable Integer id) {
        return recursoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Recurso createRecurso(@RequestBody Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    @PutMapping("/{id}")
    public Recurso updateRecurso(@PathVariable Integer id, @RequestBody Recurso recursoDetails) {
        Recurso recurso = recursoRepository.findById(id).orElse(null);
        if (recurso != null) {
            recurso.setNombre(recursoDetails.getNombre());
            recurso.setDescripcion(recursoDetails.getDescripcion());
            recurso.setFechaIngreso(recursoDetails.getFechaIngreso());
            recurso.setCategoria(recursoDetails.getCategoria());
            return recursoRepository.save(recurso);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRecurso(@PathVariable Integer id) {
        recursoRepository.deleteById(id);
    }
}
