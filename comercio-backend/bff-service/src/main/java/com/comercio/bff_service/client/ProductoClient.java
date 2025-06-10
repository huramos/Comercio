package com.comercio.bff_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.comercio.bff_service.model.Producto;

@FeignClient(name = "producto-service", url = "${producto.service.url}")
public interface ProductoClient {
    @GetMapping("/api/productos")
    List<Producto> getAllProductos();
}
