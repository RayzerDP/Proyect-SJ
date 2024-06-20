package com.example.SSjApi.controller;

import com.example.SSjApi.dto.PedidoDTO;
import com.example.SSjApi.entity.AreaTrabajo;
import com.example.SSjApi.entity.Pedido;
import com.example.SSjApi.exception.ResourceNotFoundException;
import com.example.SSjApi.repository.AreaTrabajoRepository;
import com.example.SSjApi.repository.PedidoRepository;
import com.example.SSjApi.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private AreaTrabajoRepository areaTrabajoRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<PedidoDTO> getPedidosByUsuario(@PathVariable Integer idUsuario) {
        return pedidoService.getPedidosByUsuarioId(idUsuario);
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        if (pedido.getAreaTrabajo() != null) {
            AreaTrabajo areaTrabajo = areaTrabajoRepository.findById(pedido.getAreaTrabajo().getIdAreaTrabajo())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "AreaTrabajo not found for this id :: " + pedido.getAreaTrabajo().getIdAreaTrabajo()));
            pedido.setAreaTrabajo(areaTrabajo);
        }

        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Integer id, @RequestBody Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido not found for this id :: " + id));

        pedido.setNombreProducto(pedidoDetails.getNombreProducto());
        pedido.setDescripcion(pedidoDetails.getDescripcion());
        pedido.setFechaIngreso(pedidoDetails.getFechaIngreso());
        pedido.setFechaCreacion(pedidoDetails.getFechaCreacion());
        pedido.setCantidad(pedidoDetails.getCantidad());
        pedido.setObservaciones(pedidoDetails.getObservaciones());
        pedido.setProveedor(pedidoDetails.getProveedor());
        pedido.setPrecioUnitario(pedidoDetails.getPrecioUnitario());

        if (pedidoDetails.getAreaTrabajo() != null) {
            AreaTrabajo areaTrabajo = areaTrabajoRepository.findById(pedidoDetails.getAreaTrabajo().getIdAreaTrabajo())
                    .orElseThrow(() -> new ResourceNotFoundException("AreaTrabajo not found for this id :: "
                            + pedidoDetails.getAreaTrabajo().getIdAreaTrabajo()));
            pedido.setAreaTrabajo(areaTrabajo);
        }

        final Pedido updatedPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePedido(@PathVariable Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido not found for this id :: " + id));

        pedidoRepository.delete(pedido);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> updatePedidoEstado(@PathVariable Integer id,
            @RequestBody Map<String, String> estado) {
        Pedido updatedPedido = pedidoService.updatePedidoEstado(id, estado.get("estado"));
        return ResponseEntity.ok(updatedPedido);
    }

    @GetMapping("/pedidos-por-area")
    public List<Object[]> countPedidosByArea() {
        return pedidoService.countPedidosByArea();
    }
}
