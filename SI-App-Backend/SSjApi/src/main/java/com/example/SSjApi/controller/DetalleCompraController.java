package com.example.SSjApi.controller;

import com.example.SSjApi.entity.DetalleCompra;
import com.example.SSjApi.repository.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-compras")
public class DetalleCompraController {
    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @GetMapping
    public List<DetalleCompra> getAllDetalleCompras() {
        return detalleCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public DetalleCompra getDetalleCompraById(@PathVariable Integer id) {
        return detalleCompraRepository.findById(id).orElse(null);
    }

    @PostMapping
    public DetalleCompra createDetalleCompra(@RequestBody DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    @PutMapping("/{id}")
    public DetalleCompra updateDetalleCompra(@PathVariable Integer id,
            @RequestBody DetalleCompra detalleCompraDetails) {
        DetalleCompra detalleCompra = detalleCompraRepository.findById(id).orElse(null);
        if (detalleCompra != null) {
            detalleCompra.setCompra(detalleCompraDetails.getCompra());
            detalleCompra.setRecurso(detalleCompraDetails.getRecurso());
            detalleCompra.setCantidad(detalleCompraDetails.getCantidad());
            detalleCompra.setPrecioUnitario(detalleCompraDetails.getPrecioUnitario());
            return detalleCompraRepository.save(detalleCompra);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDetalleCompra(@PathVariable Integer id) {
        detalleCompraRepository.deleteById(id);
    }
}
