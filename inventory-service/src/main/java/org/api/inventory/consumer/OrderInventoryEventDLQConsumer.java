package org.api.inventory.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.common.core.async.RabbitConsumer;
import org.common.core.dto.orderService.event.OrderEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class OrderInventoryEventDLQConsumer implements RabbitConsumer<Message> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues ={"order.inventory.queue.dlq"})
    @Override
    public void receiveEvent(Message failedOrderEventMessage) throws Exception {
        System.out.println(new String(failedOrderEventMessage.getBody() , StandardCharsets.UTF_8));
        System.out.println("Receiving Failed {order.inventory.queue.dlq} messages " + failedOrderEventMessage.toString());

        Object o = failedOrderEventMessage.getMessageProperties()
                .getHeaders().get("x-msg-retry-count");

        Integer retry = 1;

        if(Objects.nonNull(o)){
            retry = (Integer) o;
        }
        if(retry <= 3){



            System.out.println("retrying the message again - retry : " + retry);

            retry++;
            failedOrderEventMessage.getMessageProperties()
                    .getHeaders().put("x-msg-retry-count",retry);
            rabbitTemplate.convertAndSend(
                    "order.event.topic.exchange",
                    "order.event.inventory" ,
                    failedOrderEventMessage
            );
        }else {
            System.out.println("message maximum retry reached - sending to parking lot");
            System.out.println(failedOrderEventMessage.getMessageProperties().getReceivedRoutingKey());
            rabbitTemplate.convertAndSend("main.dl.fanout.parking.exchange","",failedOrderEventMessage);
        }

    }

}
