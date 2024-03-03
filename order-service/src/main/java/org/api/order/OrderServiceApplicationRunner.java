package org.api.order;

import org.api.order.entity.Order;
import org.api.order.entity.OrderItem;
import org.api.order.repository.OrderRepository;
import org.common.core.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
//@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class OrderServiceApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplicationRunner.class, args);
    }

    @Autowired
    private OrderRepository orderRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void addOrders(){
        List<OrderItem> itemList = new ArrayList<>();


        Order build = Order.builder()
                .itemCount(23)
                .status(OrderStatus.PROCESSING)
                .customerId(1)
                .totalAmount(198767.987)
                .updatedAt(new Date(System.currentTimeMillis()))
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
        for (int i = 0; i < 10; i++) {
            OrderItem build1 = OrderItem.builder().itemId(i)
                    .updatedAt(new Date(System.currentTimeMillis()))
                    .createdAt(new Date(System.currentTimeMillis()))
                    .quantity(1).order(build).build();
            itemList.add(build1);
        }
        build.setOrderItemList(itemList);
        orderRepository.save(build);
    }
}