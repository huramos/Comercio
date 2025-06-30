package com.comercio.rabbitmq_service.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String VENTAS_QUEUE = "ventas.queue";
    public static final String VENTAS_EXCHANGE = "ventas.exchange";
    public static final String VENTAS_ROUTING_KEY = "ventas.routingkey";
    public static final String PROMOCIONES_QUEUE = "promociones.queue";
    public static final String PROMOCIONES_EXCHANGE = "promociones-exchange";
    public static final String PROMOCIONES_ROUTING_KEY = "promocion.event";
    public static final String LOGIN_QUEUE = "login.queue";
    public static final String LOGIN_EXCHANGE = "login.exchange";
    public static final String LOGIN_ROUTING_KEY = "login.event";

    @Bean
    public TopicExchange ventasExchange() {
        return new TopicExchange(VENTAS_EXCHANGE, true, false);
    }

    @Bean
    public Queue ventasQueue() {
        return new Queue(VENTAS_QUEUE, true);
    }

    @Bean
    public Binding ventasBinding(Queue ventasQueue, TopicExchange ventasExchange) {
        return BindingBuilder.bind(ventasQueue).to(ventasExchange).with(VENTAS_ROUTING_KEY);
    }

    @Bean
    public TopicExchange promocionesExchange() {
        return new TopicExchange(PROMOCIONES_EXCHANGE, true, false);
    }

    @Bean
    public Queue promocionesQueue() {
        return new Queue(PROMOCIONES_QUEUE, true);
    }

    @Bean
    public Binding promocionesBinding(Queue promocionesQueue, TopicExchange promocionesExchange) {
        return BindingBuilder.bind(promocionesQueue).to(promocionesExchange).with(PROMOCIONES_ROUTING_KEY);
    }

    @Bean
    public TopicExchange loginExchange() {
        return new TopicExchange(LOGIN_EXCHANGE, true, false);
    }

    @Bean
    public Queue loginQueue() {
        return new Queue(LOGIN_QUEUE, true);
    }

    @Bean
    public Binding loginBinding(Queue loginQueue, TopicExchange loginExchange) {
        return BindingBuilder.bind(loginQueue).to(loginExchange).with(LOGIN_ROUTING_KEY);
    }
}