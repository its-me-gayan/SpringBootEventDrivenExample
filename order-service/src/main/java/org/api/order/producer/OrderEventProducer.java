package org.api.order.producer;

import org.common.core.async.RabbitProducer;
import org.common.core.dto.orderService.event.OrderEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer implements RabbitProducer<OrderEvent>
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishEvent(OrderEvent orderEvent) throws Exception {

        rabbitTemplate.convertAndSend("order.event.topic.exchange","order.event.all",orderEvent);
        System.out.println("order event published");

    }
}
