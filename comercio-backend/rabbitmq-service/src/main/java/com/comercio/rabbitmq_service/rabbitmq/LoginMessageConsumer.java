package com.comercio.rabbitmq_service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoginMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(LoginMessageConsumer.class);

    @Autowired
    private LoginEventRepository loginEventRepository;

    @RabbitListener(queues = RabbitMQConfig.LOGIN_QUEUE) // Cambiado de QUEUE_NAME a LOGIN_QUEUE
    public void receiveMessage(String message) {
        try {
            logger.info("Mensaje recibido de RabbitMQ: {}", message);
            String username = extractUsername(message);
            LoginEvent loginEvent = new LoginEvent();
            loginEvent.setUsername(username);
            loginEvent.setEventTimestamp(LocalDateTime.now());
            loginEvent.setMessage(message);

            LoginEvent savedEvent = loginEventRepository.save(loginEvent);
            logger.info("Evento guardado con ID: {} en tabla login_events", savedEvent.getId());
        } catch (Exception e) {
            logger.error("Error procesando mensaje: {}", e.getMessage(), e);
        }
    }

    private String extractUsername(String message) {
        if (message != null && message.contains("Usuario") && message.contains("ha iniciado sesión")) {
            int startIndex = message.indexOf("Usuario ") + "Usuario ".length();
            int endIndex = message.indexOf(" ha iniciado sesión");
            if (startIndex >= 0 && endIndex >= 0 && startIndex < endIndex) {
                return message.substring(startIndex, endIndex).trim();
            }
        }
        logger.warn("Formato de mensaje no reconocido, usando fallback: {}", message);
        return "unknown_" + System.currentTimeMillis();
    }
}