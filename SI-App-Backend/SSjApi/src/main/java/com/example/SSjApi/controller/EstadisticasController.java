package com.example.SSjApi.controller;

import com.example.SSjApi.service.UsuarioService;
import com.example.SSjApi.service.ProveedorService;
import com.example.SSjApi.service.RecursoService;
import com.example.SSjApi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private RecursoService recursoService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public Map<String, Object> getEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("totalUsuarios", usuarioService.countUsuarios());
        estadisticas.put("totalProveedores", proveedorService.countProveedores());

        estadisticas.put("totalPedidos", pedidoService.countPedidos());
        estadisticas.put("productosMasPedidos", pedidoService.getProductosMasPedidos());
        return estadisticas;
    }
}
