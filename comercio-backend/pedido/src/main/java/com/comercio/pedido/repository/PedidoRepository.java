package com.comercio.pedido.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.comercio.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByIdUsuario(Long idUsuario);
}

