package com.example.SSjApi.controller;

import com.example.SSjApi.entity.Proveedor;
import com.example.SSjApi.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Proveedor getProveedorById(@PathVariable Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedorDetails) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            proveedor.setRazonSocial(proveedorDetails.getRazonSocial());
            proveedor.setCodigoTributario(proveedorDetails.getCodigoTributario());
            proveedor.setDireccion(proveedorDetails.getDireccion());
            proveedor.setEmail(proveedorDetails.getEmail());
            proveedor.setFono(proveedorDetails.getFono());
            return proveedorRepository.save(proveedor);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Integer id) {
        proveedorRepository.deleteById(id);
    }
}
