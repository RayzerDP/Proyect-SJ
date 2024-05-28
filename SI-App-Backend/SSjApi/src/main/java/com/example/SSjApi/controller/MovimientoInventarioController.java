package com.example.SSjApi.controller;

import com.example.SSjApi.entity.MovimientoInventario;
import com.example.SSjApi.repository.MovimientoInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos-inventario")
public class MovimientoInventarioController {
    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @GetMapping
    public List<MovimientoInventario> getAllMovimientosInventario() {
        return movimientoInventarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public MovimientoInventario getMovimientoInventarioById(@PathVariable Integer id) {
        return movimientoInventarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public MovimientoInventario createMovimientoInventario(@RequestBody MovimientoInventario movimientoInventario) {
        return movimientoInventarioRepository.save(movimientoInventario);
    }

    @PutMapping("/{id}")
    public MovimientoInventario updateMovimientoInventario(@PathVariable Integer id,
            @RequestBody MovimientoInventario movimientoInventarioDetails) {
        MovimientoInventario movimientoInventario = movimientoInventarioRepository.findById(id).orElse(null);
        if (movimientoInventario != null) {
            movimientoInventario.setTipoMovimiento(movimientoInventarioDetails.getTipoMovimiento());
            movimientoInventario.setFechaMovimiento(movimientoInventarioDetails.getFechaMovimiento());
            movimientoInventario.setUsuario(movimientoInventarioDetails.getUsuario());
            return movimientoInventarioRepository.save(movimientoInventario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteMovimientoInventario(@PathVariable Integer id) {
        movimientoInventarioRepository.deleteById(id);
    }
}
