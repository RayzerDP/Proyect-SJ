package com.example.SSjApi.repository;

import com.example.SSjApi.dto.RecursoPorCategoriaDTO;
import com.example.SSjApi.entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    List<Recurso> findByAreaTrabajo_IdAreaTrabajo(Integer idAreaTrabajo);

    // Agregar el método findRecursosRecientes
    List<Recurso> findTop10ByOrderByFechaIngresoDesc();

    @Query("SELECT new com.example.SSjApi.dto.RecursoPorCategoriaDTO(r.categoria.descripcion, SUM(r.cantidad)) " +
            "FROM Recurso r GROUP BY r.categoria.descripcion")
    List<RecursoPorCategoriaDTO> countRecursosByCategoria();

    @Query("SELECT r FROM Recurso r WHERE r.cantidad < r.cantidadMinima")
    List<Recurso> findByCantidadLessThanCantidadMinima();

    @Query("SELECT r FROM Recurso r WHERE r.cantidad < r.cantidadMinima AND r.usuario.idUsuario = :userId")
    List<Recurso> findByCantidadLessThanCantidadMinimaAndUsuarioId(@Param("userId") Integer userId);
}
