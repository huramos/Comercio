package com.comercio.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comercio.producto.model.Producto;
import com.comercio.producto.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepo;

    public List<Producto> listarProductos() {
        return productoRepo.findAll();
    }

    public Producto crearProducto(Producto producto) {
        return productoRepo.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto nuevoProducto) {
        Producto producto = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(nuevoProducto.getNombre());
        producto.setDescripcion(nuevoProducto.getDescripcion());
        producto.setPrecio(nuevoProducto.getPrecio());
        producto.setStock(nuevoProducto.getStock());
        return productoRepo.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepo.deleteById(id);
    }
}
