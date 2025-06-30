package com.comercio.rabbitmq_service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class PromocionConsumer {
    private static final Logger logger = LoggerFactory.getLogger(PromocionConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.PROMOCIONES_QUEUE) // Usa PROMOCIONES_QUEUE en lugar de "promociones"
    public void processPromocion(String message) {
        try {
            logger.info("Mensaje recibido de la cola promociones: {}", message);
            String fileName = "promocion_" + System.currentTimeMillis() + ".json";
            try (FileWriter file = new FileWriter(fileName)) {
                file.write(message);
                file.flush();
                logger.info("Archivo JSON generado: {}", fileName);
            }
        } catch (IOException e) {
            logger.error("Error al generar archivo JSON: {}", e.getMessage(), e);
        }
    }
}