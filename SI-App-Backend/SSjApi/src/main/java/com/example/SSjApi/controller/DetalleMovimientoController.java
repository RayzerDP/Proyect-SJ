package com.example.SSjApi.controller;

import com.example.SSjApi.entity.DetalleMovimiento;
import com.example.SSjApi.repository.DetalleMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-movimientos")
public class DetalleMovimientoController {
    @Autowired
    private DetalleMovimientoRepository detalleMovimientoRepository;

    @GetMapping
    public List<DetalleMovimiento> getAllDetalleMovimientos() {
        return detalleMovimientoRepository.findAll();
    }

    @GetMapping("/{id}")
    public DetalleMovimiento getDetalleMovimientoById(@PathVariable Integer id) {
        return detalleMovimientoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public DetalleMovimiento createDetalleMovimiento(@RequestBody DetalleMovimiento detalleMovimiento) {
        return detalleMovimientoRepository.save(detalleMovimiento);
    }

    @PutMapping("/{id}")
    public DetalleMovimiento updateDetalleMovimiento(@PathVariable Integer id,
            @RequestBody DetalleMovimiento detalleMovimientoDetails) {
        DetalleMovimiento detalleMovimiento = detalleMovimientoRepository.findById(id).orElse(null);
        if (detalleMovimiento != null) {
            detalleMovimiento.setMovimiento(detalleMovimientoDetails.getMovimiento());
            detalleMovimiento.setRecurso(detalleMovimientoDetails.getRecurso());
            detalleMovimiento.setCantidad(detalleMovimientoDetails.getCantidad());
            return detalleMovimientoRepository.save(detalleMovimiento);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDetalleMovimiento(@PathVariable Integer id) {
        detalleMovimientoRepository.deleteById(id);
    }
}
