package com.example.SSjApi.repository;

import com.example.SSjApi.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    @Query("SELECT c FROM Compra c ORDER BY c.fecha DESC")
    List<Compra> findUltimasCompras();

}
