package org.api.order.service;

import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.orderService.OrderCreateResponseDto;
import org.common.core.dto.orderService.OrderRequestDto;
import org.common.core.dto.orderService.OrderResponseDto;

import java.util.List;

public interface OrderService {
    AbstractResponse<OrderCreateResponseDto> createOrder(OrderRequestDto orderRequestDto)throws Exception;
    AbstractResponse<List<OrderResponseDto>> getAllOrders()throws Exception;
}
