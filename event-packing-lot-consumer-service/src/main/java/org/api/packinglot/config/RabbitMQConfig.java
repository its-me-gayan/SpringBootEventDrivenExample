package org.api.packinglot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
       return QueueBuilder.durable("main.dl.fanout.parking.queue")
               .ttl(50000)
                .build();
    }

    @Bean
    FanoutExchange parkingFanOutExchange() {
        return new FanoutExchange("main.dl.fanout.parking.exchange");
    }


    @Bean
    Binding binding(Queue queue, FanoutExchange parkingFanOutExchange) {

        return BindingBuilder.bind(queue).to(parkingFanOutExchange);
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
