package com.comercio.rabbitmq_service.rabbitmq;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaEventRepository extends JpaRepository<VentaEvent, Long> {
}