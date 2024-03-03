package org.api.order.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
//    @Bean
//    Queue queue() {
//        return new Queue("order.event.queue", false);
//    }

//    @Bean
//    FanoutExchange orderEventFanOutExchange() {
//        return new FanoutExchange("order.events.fanout.exchange");
//    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("order.#");
//    }
    @Bean
    public org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter, ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }
}
