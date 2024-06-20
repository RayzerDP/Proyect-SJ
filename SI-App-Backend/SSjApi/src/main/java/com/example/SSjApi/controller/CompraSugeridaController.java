package com.example.SSjApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.service.CompraSugeridaService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompraSugeridaController {

    @Autowired
    private CompraSugeridaService compraSugeridaService;

    @GetMapping("/compras-sugeridas")
    public ResponseEntity<List<Recurso>> obtenerComprasSugeridas(@RequestParam Integer idUsuario) {
        List<Recurso> comprasSugeridas = compraSugeridaService.obtenerComprasSugeridas(idUsuario);
        if (comprasSugeridas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comprasSugeridas, HttpStatus.OK);
    }
}
