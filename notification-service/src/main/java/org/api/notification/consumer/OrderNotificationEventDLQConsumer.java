package org.api.notification.consumer;

import org.common.core.async.RabbitConsumer;
import org.common.core.dto.orderService.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderNotificationEventDLQConsumer implements RabbitConsumer<OrderEvent> {

    @RabbitListener(queues ={"order.notification.queue.dlq"})
    @Override
    public void receiveEvent(OrderEvent orderEvent) throws Exception {
        System.out.println("Receiving Failed {order.notification.queue.dlq} messages " + orderEvent.toString());
    }

}
