package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Categoria;
import com.example.SSjApi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaDetails) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            categoria.setDescripcion(categoriaDetails.getDescripcion());
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
    }
}
