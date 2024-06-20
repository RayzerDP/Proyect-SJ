package com.example.SSjApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.repository.RecursoRepository;
import java.util.List;

@Service
public class CompraSugeridaService {

    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> obtenerComprasSugeridas() {
        return recursoRepository.findByCantidadLessThanCantidadMinima();
    }

    public List<Recurso> obtenerComprasSugeridasPorUsuario(Integer userId) {
        return recursoRepository.findByCantidadLessThanCantidadMinimaAndUsuarioId(userId);
    }

    public List<Recurso> obtenerComprasSugeridas(Integer idUsuario) {
        return recursoRepository.findByCantidadLessThanCantidadMinimaAndUsuarioId(idUsuario);
    }
}
