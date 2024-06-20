package com.example.SSjApi.repository;

import com.example.SSjApi.dto.PedidosPorUsuarioDTO;
import com.example.SSjApi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByIdUsuario(Integer idUsuario);

    @Query("SELECT p, u.nombreUsuario FROM Pedido p JOIN Usuario u ON p.idUsuario = u.idUsuario WHERE p.idUsuario = :idUsuario")
    List<Object[]> findPedidosByUsuarioId(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT a.nombreArea, COUNT(p) FROM Pedido p JOIN p.areaTrabajo a GROUP BY a.nombreArea")
    List<Object[]> countPedidosByArea();

    @Query("SELECT p.nombreProducto, COUNT(p), SUM(p.cantidad) FROM Pedido p GROUP BY p.nombreProducto ORDER BY COUNT(p) DESC")
    List<Object[]> findProductosMasPedidos();

    @Query("SELECT new com.example.SSjApi.dto.PedidosPorUsuarioDTO(p.nombreProducto, p.fechaIngreso, p.fechaCreacion, p.cantidad, p.proveedor, p.precioUnitario, u.nombreUsuario) "
            +
            "FROM Pedido p JOIN Usuario u ON p.idUsuario = u.idUsuario")
    List<PedidosPorUsuarioDTO> findAllPedidosConDetalles();

}
