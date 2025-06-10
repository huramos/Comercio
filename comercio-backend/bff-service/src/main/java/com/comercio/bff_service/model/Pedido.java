package com.comercio.bff_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private LocalDateTime fecha;
    private double total;
    
    public Long getId() {
        return id;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public double getTotal() {
        return total;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
