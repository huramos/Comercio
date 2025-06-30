package com.comercio.promociones.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.comercio.promociones.model.Promocion;
import com.comercio.promociones.service.PromocionService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {
    @Autowired
    private PromocionService promocionService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Promocion crear(@RequestBody Promocion promocion) {
        Promocion savedPromocion = promocionService.crearPromocion(promocion);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(savedPromocion);
            rabbitTemplate.convertAndSend("promociones-exchange", "promocion.event", message);
            System.out.println("Mensaje enviado a RabbitMQ - Exchange: promociones-exchange, Routing Key: promocion.event, Mensaje: " + message);
        } catch (Exception e) {
            System.err.println("Error enviando mensaje a RabbitMQ: " + e.getMessage());
            e.printStackTrace();
        }
        return savedPromocion;
    }
}