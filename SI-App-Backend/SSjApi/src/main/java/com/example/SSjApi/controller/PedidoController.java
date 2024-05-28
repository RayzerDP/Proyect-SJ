package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Pedido;
import com.example.SSjApi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public Pedido updatePedido(@PathVariable Integer id, @RequestBody Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setFechaCreacion(pedidoDetails.getFechaCreacion());
            pedido.setEstado(pedidoDetails.getEstado());
            pedido.setObservaciones(pedidoDetails.getObservaciones());
            pedido.setUsuario(pedidoDetails.getUsuario());
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Integer id) {
        pedidoRepository.deleteById(id);
    }
}
