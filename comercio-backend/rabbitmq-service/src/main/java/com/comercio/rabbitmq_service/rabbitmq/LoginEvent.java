package com.comercio.rabbitmq_service.rabbitmq;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_events")
@Data
public class LoginEvent {
    @Id
    @SequenceGenerator(name = "login_seq", sequenceName = "login_events_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "event_timestamp", nullable = false)
    private LocalDateTime eventTimestamp;

    @Column(name = "message", nullable = false)
    private String message;
}