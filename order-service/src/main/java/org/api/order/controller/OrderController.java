package org.api.order.controller;

import org.api.order.service.OrderService;
import org.common.core.dto.common.AbstractRequest;
import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.orderService.OrderCreateResponseDto;
import org.common.core.dto.orderService.OrderRequestDto;
import org.common.core.dto.orderService.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/")
    public ResponseEntity<AbstractResponse<OrderCreateResponseDto>> createOrder(@RequestBody AbstractRequest<OrderRequestDto> orderRequest)throws Exception{
        AbstractResponse<OrderCreateResponseDto> order = orderService.createOrder(orderRequest.getData());
        return ResponseEntity.status(order.getStatus()).body(order);
    }
    @GetMapping("/")
    public ResponseEntity<AbstractResponse<List<OrderResponseDto>>> getAllOrder()throws Exception{
        AbstractResponse<List<OrderResponseDto>> allOrders = orderService.getAllOrders();
        return ResponseEntity.status(allOrders.getStatus()).body(allOrders);
    }

}
