package com.example.SSjApi.service;

import com.example.SSjApi.dto.RecursoPorCategoriaDTO;
import com.example.SSjApi.entity.AreaTrabajo;
import com.example.SSjApi.entity.Categoria;
import com.example.SSjApi.entity.Recurso;
import com.example.SSjApi.entity.Usuario;
import com.example.SSjApi.exception.ResourceNotFoundException;
import com.example.SSjApi.repository.AreaTrabajoRepository;
import com.example.SSjApi.repository.CategoriaRepository;
import com.example.SSjApi.repository.RecursoRepository;
import com.example.SSjApi.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AreaTrabajoRepository areaTrabajoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Recurso> getRecursosByAreaTrabajo(Integer idAreaTrabajo) {
        return recursoRepository.findByAreaTrabajo_IdAreaTrabajo(idAreaTrabajo);
    }

    public List<Recurso> getRecursosRecientes() {
        return recursoRepository.findTop10ByOrderByFechaIngresoDesc();
    }

    public Recurso createRecurso(Recurso recurso) {
        if (recurso.getAreaTrabajo() != null && recurso.getAreaTrabajo().getIdAreaTrabajo() != null) {
            AreaTrabajo areaTrabajo = areaTrabajoRepository.findById(recurso.getAreaTrabajo().getIdAreaTrabajo())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "AreaTrabajo not found for this id :: " + recurso.getAreaTrabajo().getIdAreaTrabajo()));
            recurso.setAreaTrabajo(areaTrabajo);
        }
        if (recurso.getCategoria() != null && recurso.getCategoria().getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(recurso.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Categoria not found for this id :: " + recurso.getCategoria().getIdCategoria()));
            recurso.setCategoria(categoria);
        }
        return recursoRepository.save(recurso);
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public List<Recurso> getAllRecursos() {
        return recursoRepository.findAll();
    }

    public List<AreaTrabajo> getAreasTrabajoForUser(Integer userId) {
        Usuario usuario = usuarioRepository.findByIdUsuario(userId);
        if (usuario != null && usuario.getAreaTrabajo() != null) {
            return List.of(usuario.getAreaTrabajo()); // Devolver solo el área de trabajo del usuario
        }
        return areaTrabajoRepository.findAll(); // O devolver todas las áreas de trabajo si no hay restricciones
    }

    public Recurso updateRecurso(Integer id, Recurso recurso) {
        Optional<Recurso> existingRecurso = recursoRepository.findById(id);
        if (existingRecurso.isPresent()) {
            Recurso recursoToUpdate = existingRecurso.get();
            recursoToUpdate.setNombre(recurso.getNombre());
            recursoToUpdate.setDescripcion(recurso.getDescripcion());
            recursoToUpdate.setCantidad(recurso.getCantidad());
            recursoToUpdate.setFechaIngreso(recurso.getFechaIngreso());
            recursoToUpdate.setObservaciones(recurso.getObservaciones());
            recursoToUpdate.setProveedor(recurso.getProveedor());

            // Actualizar relaciones
            if (recurso.getAreaTrabajo() != null) {
                AreaTrabajo areaTrabajo = areaTrabajoRepository.findById(recurso.getAreaTrabajo().getIdAreaTrabajo())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "AreaTrabajo not found for this id :: " + recurso.getAreaTrabajo().getIdAreaTrabajo()));
                recursoToUpdate.setAreaTrabajo(areaTrabajo);
            }

            if (recurso.getCategoria() != null) {
                Categoria categoria = categoriaRepository.findById(recurso.getCategoria().getIdCategoria())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Categoria not found for this id :: " + recurso.getCategoria().getIdCategoria()));
                recursoToUpdate.setCategoria(categoria);
            }

            return recursoRepository.save(recursoToUpdate);
        } else {
            return null;
        }
    }

    public void deleteRecurso(Integer id) {
        recursoRepository.deleteById(id);
    }

    public List<RecursoPorCategoriaDTO> getRecursosPorCategoria() {
        return recursoRepository.countRecursosByCategoria();
    }

}
