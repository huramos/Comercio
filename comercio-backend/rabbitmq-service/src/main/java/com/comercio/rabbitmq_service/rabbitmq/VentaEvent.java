package com.comercio.rabbitmq_service.rabbitmq;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ventas_events")
@Data
public class VentaEvent {
    @Id
    @SequenceGenerator(name = "venta_seq", sequenceName = "ventas_events_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "total", nullable = false)
    private double total;
}