package com.comercio.pedido.controller;

import com.comercio.pedido.model.Pedido;
import com.comercio.pedido.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.listarPedidos();
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        Pedido savedPedido = pedidoService.crearPedido(pedido);
        try {
            String message = objectMapper.writeValueAsString(savedPedido);
            rabbitTemplate.convertAndSend("ventas.exchange", "ventas.routingkey", message);
            System.out.println("Mensaje enviado a RabbitMQ - Exchange: ventas.exchange, Routing Key: ventas.routingkey, Mensaje: " + message);
        } catch (Exception e) {
            System.err.println("Error enviando mensaje a RabbitMQ: " + e.getMessage());
            e.printStackTrace();
        }
        return savedPedido;
    }

    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.ok().build();
    }
}