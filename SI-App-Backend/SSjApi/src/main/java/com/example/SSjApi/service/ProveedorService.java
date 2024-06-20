package com.example.SSjApi.service;

import com.example.SSjApi.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public long countProveedores() {
        return proveedorRepository.count();
    }
}
