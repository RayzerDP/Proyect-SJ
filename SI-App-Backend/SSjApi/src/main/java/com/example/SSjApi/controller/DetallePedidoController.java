package com.example.SSjApi.controller;

import com.example.SSjApi.entity.DetallePedido;
import com.example.SSjApi.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-pedidos")
public class DetallePedidoController {
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @GetMapping
    public List<DetallePedido> getAllDetallePedidos() {
        return detallePedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public DetallePedido getDetallePedidoById(@PathVariable Integer id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public DetallePedido createDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @PutMapping("/{id}")
    public DetallePedido updateDetallePedido(@PathVariable Integer id,
            @RequestBody DetallePedido detallePedidoDetails) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id).orElse(null);
        if (detallePedido != null) {
            detallePedido.setPedido(detallePedidoDetails.getPedido());
            detallePedido.setRecurso(detallePedidoDetails.getRecurso());
            detallePedido.setCantidad(detallePedidoDetails.getCantidad());
            detallePedido.setPrecioUnitario(detallePedidoDetails.getPrecioUnitario());
            return detallePedidoRepository.save(detallePedido);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDetallePedido(@PathVariable Integer id) {
        detallePedidoRepository.deleteById(id);
    }
}
