package com.comercio.rabbitmq_service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(VentaConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VentaEventRepository ventaEventRepository;

    @RabbitListener(queues = RabbitMQConfig.VENTAS_QUEUE)
    public void processVenta(String message) {
        try {
            logger.info("Mensaje recibido de la cola ventas: {}", message);
            VentaEvent venta = objectMapper.readValue(message, VentaEvent.class);
            ventaEventRepository.save(venta);
            logger.info("Venta guardada en Oracle: idUsuario={}, total={}", venta.getIdUsuario(), venta.getTotal());
        } catch (Exception e) {
            logger.error("Error procesando mensaje de ventas: {}", e.getMessage(), e);
        }
    }
}