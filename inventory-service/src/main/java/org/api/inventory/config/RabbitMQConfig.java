package org.api.inventory.config;

import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private ConnectionFactory cachingConnectionFactory;
    @Bean
    Queue queue() {
       return QueueBuilder.durable("order.inventory.queue")
               .deadLetterRoutingKey("x-dead-letter-inventory")
               .deadLetterExchange("order.event.topic.exchange.dlq")
               .ttl(10000)
                .build();
    }
    @Bean
    Queue queueDlq() {
        return QueueBuilder.durable("order.inventory.queue.dlq")
                .build();
    }
    @Bean
    TopicExchange orderEventTopicExchange() {
        return new TopicExchange("order.event.topic.exchange");
    }
    @Bean
    TopicExchange orderEventTopicExchangeDlq() {
       return new TopicExchange("order.event.topic.exchange.dlq");
        //return new FanoutExchange("order.event.fanout.exchange.dlq");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange orderEventTopicExchange) {

        return BindingBuilder.bind(queue).to(orderEventTopicExchange).with("order.event.inventory");
    }
    @Bean
    Binding bindingAll(Queue queue, TopicExchange orderEventTopicExchange) {
        return BindingBuilder.bind(queue).to(orderEventTopicExchange).with("order.event.all");
    }
    @Bean
    Binding bindingDlq(Queue queueDlq, TopicExchange orderEventTopicExchangeDlq) {

        return BindingBuilder.bind(queueDlq).to(orderEventTopicExchangeDlq).with("x-dead-letter-inventory");
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
