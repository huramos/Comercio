package com.comercio.pedido.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comercio.pedido.model.Pedido;
import com.comercio.pedido.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepo;

    public List<Pedido>listarPedidos(){
        return pedidoRepo.findAll();
    }

    public Pedido crearPedido (Pedido pedido){
        pedido.setFecha(LocalDateTime.now());
        return pedidoRepo.save(pedido);
    }

    public Pedido actualizarPedido(Long id, Pedido nuevoPedido){
        Pedido pedido = pedidoRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setIdUsuario(nuevoPedido.getIdUsuario());
        pedido.setTotal(nuevoPedido.getTotal());
        return pedidoRepo.save(pedido);
    }

    public void eliminarPedido(Long id){
        pedidoRepo.deleteById(id);
    }
    
}
