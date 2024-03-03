package org.common.core.dto.orderService;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderCreateResponseDto {
    private Integer orderId;
    private LocalDateTime dateTime;
}
