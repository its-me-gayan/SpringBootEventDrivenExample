package org.common.core.dto.orderService;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class OrderRequestDto {
    private int customerId;
    @JsonFormat(pattern = "yyyyMMdd")
    private Date orderDate;
    private List<ItemData> itemList;
}
