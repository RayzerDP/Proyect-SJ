package com.example.SSjApi.service;

import com.example.SSjApi.entity.Compra;
import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.repository.CompraRepository;
import com.example.SSjApi.repository.PedidoRepository;
import com.example.SSjApi.repository.ProveedorRepository;
import com.example.SSjApi.repository.RecursoRepository;
import com.example.SSjApi.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Compra> getUltimasCompras() {
        return compraRepository.findAll();
    }

    public List<Recurso> getRecursosRecientes() {
        return recursoRepository.findAll();
    }

    public long countTotalUsuarios() {
        return usuarioRepository.count();
    }

    public long countTotalProveedores() {
        return proveedorRepository.count();
    }

    public long countTotalPedidos() {
        return pedidoRepository.count();
    }

    public List<Object[]> getProductosMasPedidos() {
        return pedidoRepository.findProductosMasPedidos();
    }

    public long countTotalRecursos() {
        return recursoRepository.count();
    }
}
