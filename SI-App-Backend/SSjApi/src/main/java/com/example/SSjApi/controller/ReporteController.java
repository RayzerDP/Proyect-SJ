// ReportController.java
package com.example.SSjApi.controller;

import com.example.SSjApi.service.ReporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/pedidos-por-usuario")
    public ResponseEntity<InputStreamResource> downloadPedidosPorUsuarioReport() {
        ByteArrayInputStream bis = reporteService.generarReportePedidosConDetalles();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pedidos_por_usuario.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
