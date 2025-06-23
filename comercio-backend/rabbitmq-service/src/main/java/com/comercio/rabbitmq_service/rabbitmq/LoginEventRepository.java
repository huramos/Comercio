package com.comercio.rabbitmq_service.rabbitmq;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginEventRepository extends JpaRepository<LoginEvent, Long> {
}