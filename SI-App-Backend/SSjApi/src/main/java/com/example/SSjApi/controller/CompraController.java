package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Compra;
import com.example.SSjApi.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;

    @GetMapping
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Compra getCompraById(@PathVariable Integer id) {
        return compraRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Compra createCompra(@RequestBody Compra compra) {
        return compraRepository.save(compra);
    }

    @PutMapping("/{id}")
    public Compra updateCompra(@PathVariable Integer id, @RequestBody Compra compraDetails) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra != null) {
            compra.setFecha(compraDetails.getFecha());
            compra.setDatosAdicionales(compraDetails.getDatosAdicionales());
            return compraRepository.save(compra);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCompra(@PathVariable Integer id) {
        compraRepository.deleteById(id);
    }
}
