package org.api.order.service.Impl;

import org.api.order.entity.Order;
import org.api.order.entity.OrderItem;
import org.api.order.producer.OrderEventProducer;
import org.api.order.repository.OrderRepository;
import org.api.order.service.OrderService;
import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.itemService.ItemDto;
import org.common.core.dto.orderService.ItemData;
import org.common.core.dto.orderService.OrderCreateResponseDto;
import org.common.core.dto.orderService.OrderRequestDto;
import org.common.core.dto.orderService.OrderResponseDto;
import org.common.core.dto.orderService.event.OrderEvent;
import org.common.core.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
    private OrderRepository orderRepository;

   @Autowired
   private OrderEventProducer orderEventProducer;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public AbstractResponse<OrderCreateResponseDto> createOrder(OrderRequestDto orderRequestDto) throws Exception {

        List<ItemData> itemList1 = orderRequestDto.getItemList();

        Order order = Order.builder()
                .status(OrderStatus.PROCESSING)
                .itemCount(itemList1.size())
                .customerId(orderRequestDto.getCustomerId())
                .totalAmount(10009.00)
                .updatedAt(new Date(System.currentTimeMillis()))
                .createdAt(new Date(System.currentTimeMillis()))
                .build();

        List<OrderItem> orderItems = itemList1.stream().map(itemData -> OrderItem.builder().itemId(itemData.getId())
                .updatedAt(new Date(System.currentTimeMillis()))
                .createdAt(new Date(System.currentTimeMillis()))
                .quantity(itemData.getQuantity()).order(order).build()).toList();


        order.setOrderItemList(orderItems);
        orderRepository.save(order);

        //producing order event
        orderEventProducer.publishEvent(OrderEvent.builder().orderId(order.getId()).itemList(itemList1).build());


        return AbstractResponse.<OrderCreateResponseDto>builder()
                .data(
                        OrderCreateResponseDto.builder().orderId(order.getId()).dateTime(LocalDateTime.now()).build()
                )
                .isSuccess(Boolean.TRUE)
                .message_code("000")
                .message("Order creation Success")
                .message_description("Order creation success")
                .timestamp(Timestamp.from(Instant.now()))
                .status(HttpStatus.OK)
                .status_code(HttpStatus.OK.value()).build();
    }

    @Override
    public AbstractResponse<List<OrderResponseDto>> getAllOrders() throws Exception {

        return AbstractResponse.<List<OrderResponseDto>>builder()
                .data(
                        orderRepository.findAll().stream().map(order ->
                                OrderResponseDto.builder()
                                        .orderDate(order.getCreatedAt())
                                        .status(order.getStatus())
                                        .totalAmount(order.getTotalAmount())
                                        .customerId(order.getCustomerId())
                                        .itemList(order.getOrderItemList().stream().map(orderItem ->
                                                        ItemData.builder().itemName("")
                                                                .id(orderItem.getItemId())
                                                                .quantity(orderItem.getQuantity()).build()
                                                ).toList()
                                        ).build()
                        ).toList()
                )
                .isSuccess(Boolean.TRUE)
                .message_code("000")
                .message("Order list Success")
                .message_description("Order list success")
                .timestamp(Timestamp.from(Instant.now()))
                .status(HttpStatus.OK)
                .status_code(HttpStatus.OK.value()).build();
    }
}
