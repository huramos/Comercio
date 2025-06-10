package com.comercio.bff_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.comercio.bff_service.model.Pedido;

@FeignClient(name = "pedido-service", url = "${pedido.service.url}")
public interface PedidoClient {
    @GetMapping("/api/pedidos")
    List<Pedido> getAllPedidos();
}
