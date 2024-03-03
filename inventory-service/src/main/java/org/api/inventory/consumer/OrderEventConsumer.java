package org.api.inventory.consumer;

import org.common.core.async.RabbitConsumer;
import org.common.core.dto.orderService.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer implements RabbitConsumer<OrderEvent> {

    @RabbitListener(queues ={"order.inventory.queue"})
    @Override
    public void receiveEvent(OrderEvent orderEvent) throws Exception {
        System.out.println("order event received " + orderEvent.toString());
    }
}
