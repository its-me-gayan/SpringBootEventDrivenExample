package org.common.core.dto.orderService;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.common.core.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDto {
    private int customerId;
    private double totalAmount;
    private OrderStatus status;
    @JsonFormat(pattern = "yyyyMMdd")
    private Date orderDate;
    private List<ItemData> itemList;
}
