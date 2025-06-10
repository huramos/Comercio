package com.comercio.bff_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.bff_service.client.PedidoClient;
import com.comercio.bff_service.client.ProductoClient;
import com.comercio.bff_service.client.PromocionClient;
import com.comercio.bff_service.client.UsuarioClient;
import com.comercio.bff_service.model.Pedido;
import com.comercio.bff_service.model.Producto;
import com.comercio.bff_service.model.Promocion;
import com.comercio.bff_service.model.Usuario;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/bff")
@CrossOrigin(origins = "*") // permite conexión desde Angular
public class BffController {

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ProductoClient productoClient;

    @Autowired
    private PromocionClient promocionClient;

    @Autowired
    private PedidoClient pedidoClient;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return usuarioClient.getAllUsuarios();
    }

    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        return productoClient.getAllProductos();
    }

    @GetMapping("/promociones")
    public List<Promocion> obtenerPromociones() {
        return promocionClient.getAllPromociones();
    }

    @GetMapping("/pedidos")
    public List<Pedido> obtenerPedidos() {
        return pedidoClient.getAllPedidos();
    }
}

