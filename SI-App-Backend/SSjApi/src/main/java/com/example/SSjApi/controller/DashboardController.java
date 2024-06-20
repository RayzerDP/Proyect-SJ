package com.example.SSjApi.controller;

import com.example.SSjApi.service.DashboardService;
import com.example.SSjApi.entity.Compra;
import com.example.SSjApi.entity.Recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/data")
    public ResponseEntity<Map<String, Object>> getDashboardData() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalUsuarios", dashboardService.countTotalUsuarios());
        data.put("totalProveedores", dashboardService.countTotalProveedores());
        data.put("totalRecursos", dashboardService.countTotalRecursos());
        data.put("totalPedidos", dashboardService.countTotalPedidos());
        data.put("productosMasPedidos", dashboardService.getProductosMasPedidos());
        data.put("ultimasCompras", dashboardService.getUltimasCompras());
        data.put("recursosRecientes", dashboardService.getRecursosRecientes());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/ultimas-compras")
    public ResponseEntity<List<Compra>> getUltimasCompras() {
        return ResponseEntity.ok(dashboardService.getUltimasCompras());
    }

    @GetMapping("/recursos-recientes")
    public ResponseEntity<List<Recurso>> getRecursosRecientes() {
        return ResponseEntity.ok(dashboardService.getRecursosRecientes());
    }
}
