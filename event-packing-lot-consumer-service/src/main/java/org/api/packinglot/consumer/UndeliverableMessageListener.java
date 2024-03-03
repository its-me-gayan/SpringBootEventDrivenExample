package org.api.packinglot.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.packinglot.entity.EventStore;
import org.api.packinglot.repository.EventStoreRepository;
import org.common.core.async.RabbitConsumer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Map;

@Service
public class UndeliverableMessageListener implements RabbitConsumer<Message> {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
   private ObjectMapper objectMapper;
    @RabbitListener(queues = {"main.dl.fanout.parking.queue"})
    @Override
    public void receiveEvent(Message message) throws Exception {

        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        String payload = "N/A";
        String headerValue = "N/A";
        try{
            payload = new String(message.getBody() , StandardCharsets.UTF_8);
        }catch (Exception ex){
            System.out.println("exception occured while genrating the payload - " + ex.getMessage());
        }
        try{
            headerValue = objectMapper.writeValueAsString(headers);
        }catch (Exception ex){
            System.out.println("exception occured while genrating the header - " + ex.getMessage());
        }


        EventStore build = EventStore.builder()
                .createdAt(new Date(System.currentTimeMillis()))
                .updatedAt(new Date(System.currentTimeMillis()))
                .payload(payload)
                .header_value(headerValue)
                .org_exchange(headers.get("x-first-death-exchange") != null ? headers.get("x-first-death-exchange").toString() : "N/A")
                .org_queue(headers.get("x-first-death-queue") != null ? headers.get("x-first-death-queue").toString() : "N/A")
                .org_routing_key(headers.get("x-death") != null ? headers.get("x-death").toString() : "N/A")
                .build();

        eventStoreRepository.save(build);


    }
}
