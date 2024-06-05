package com.example.SSjApi.repository;

import com.example.SSjApi.entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    List<Recurso> findByAreaTrabajo_IdAreaTrabajo(Integer idAreaTrabajo);
}
