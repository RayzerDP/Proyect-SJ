package com.example.SSjApi.service;

import com.example.SSjApi.dto.PedidoDTO;
import com.example.SSjApi.entity.Pedido;
import com.example.SSjApi.exception.ResourceNotFoundException;
import com.example.SSjApi.repository.PedidoRepository;
import com.example.SSjApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;

        @Autowired
        private UsuarioRepository usuarioRepository;

        public PedidoDTO getPedidoById(Integer id) {
                Pedido pedido = pedidoRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Pedido not found"));
                String nombreUsuario = usuarioRepository.findById(pedido.getIdUsuario())
                                .map(usuario -> usuario.getNombre())
                                .orElse("Usuario desconocido");
                return new PedidoDTO(
                                pedido.getIdPedido(),
                                pedido.getFechaCreacion(),
                                pedido.getNombreProducto(),
                                pedido.getDescripcion(),
                                pedido.getFechaIngreso(),
                                pedido.getCantidad(),
                                pedido.getProveedor(),
                                pedido.getObservaciones(),
                                pedido.getPrecioUnitario(),
                                nombreUsuario,
                                pedido.getEstado());
        }

        public List<PedidoDTO> getAllPedidos() {
                return pedidoRepository.findAll().stream().map(pedido -> {
                        String nombreUsuario = usuarioRepository.findById(pedido.getIdUsuario())
                                        .map(usuario -> usuario.getNombre())
                                        .orElse("Usuario desconocido");
                        return new PedidoDTO(
                                        pedido.getIdPedido(),
                                        pedido.getFechaCreacion(),
                                        pedido.getNombreProducto(),
                                        pedido.getDescripcion(),
                                        pedido.getFechaIngreso(),
                                        pedido.getCantidad(),
                                        pedido.getProveedor(),
                                        pedido.getObservaciones(),
                                        pedido.getPrecioUnitario(),
                                        nombreUsuario,
                                        pedido.getEstado());
                }).collect(Collectors.toList());
        }

        public Pedido updatePedidoEstado(Integer id, String estado) {
                Pedido pedido = pedidoRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Pedido not found"));
                pedido.setEstado(estado);
                return pedidoRepository.save(pedido);
        }

        public List<PedidoDTO> getPedidosByUsuarioId(Integer idUsuario) {
                List<Pedido> pedidos = pedidoRepository.findByIdUsuario(idUsuario);
                return pedidos.stream().map(pedido -> {
                        String nombreUsuario = usuarioRepository.findById(pedido.getIdUsuario())
                                        .map(usuario -> usuario.getNombre())
                                        .orElse("Usuario desconocido");
                        return new PedidoDTO(
                                        pedido.getIdPedido(),
                                        pedido.getFechaCreacion(),
                                        pedido.getNombreProducto(),
                                        pedido.getDescripcion(),
                                        pedido.getFechaIngreso(),
                                        pedido.getCantidad(),
                                        pedido.getProveedor(),
                                        pedido.getObservaciones(),
                                        pedido.getPrecioUnitario(),
                                        nombreUsuario,
                                        pedido.getEstado());
                }).collect(Collectors.toList());
        }

        public List<Object[]> countPedidosByArea() {
                return pedidoRepository.countPedidosByArea();
        }

        public long countPedidos() {
                return pedidoRepository.count();
        }

        public List<Object[]> getProductosMasPedidos() {
                return pedidoRepository.findProductosMasPedidos();
        }
}
