package com.comercio.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comercio.producto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
