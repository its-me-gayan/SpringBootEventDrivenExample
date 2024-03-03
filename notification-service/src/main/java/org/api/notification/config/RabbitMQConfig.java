package org.api.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private ConnectionFactory cachingConnectionFactory;

    public RabbitMQConfig(ConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    Queue queue() {
        return QueueBuilder
                .durable("order.notification.queue")
                .deadLetterRoutingKey("x-dead-letter-notification")
                .deadLetterExchange("order.event.topic.exchange.dlq")
                .ttl(5000)
                .build();
    }

    @Bean
    Queue queueDlq() {
        return QueueBuilder.durable("order.notification.queue.dlq").build();
    }
    @Bean
    TopicExchange orderEventTopicExchange() {
        return new TopicExchange("order.event.topic.exchange");
    }

    @Bean
    TopicExchange orderEventTopicExchangeDlq() {
        return new TopicExchange("order.event.topic.exchange.dlq");
    }
    @Bean
    Binding binding(Queue queue, TopicExchange orderEventTopicExchange) {
        return BindingBuilder.bind(queue).to(orderEventTopicExchange).with("order.event.notification");
    }
    @Bean
    Binding bindingAll(Queue queue, TopicExchange orderEventTopicExchange) {
        return BindingBuilder.bind(queue).to(orderEventTopicExchange).with("order.event.all");
    }
    @Bean
    Binding bindingDlq(Queue queueDlq, TopicExchange orderEventTopicExchangeDlq) {
        return BindingBuilder.bind(queueDlq).to(orderEventTopicExchangeDlq).with("x-dead-letter-notification");
    }
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
