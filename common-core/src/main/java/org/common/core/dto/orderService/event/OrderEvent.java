package org.common.core.dto.orderService.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.common.core.dto.orderService.ItemData;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@ToString

public class OrderEvent implements Serializable {
    private int orderId;
    private List<ItemData> itemList;

    public OrderEvent() {
    }

    public OrderEvent(int orderId, List<ItemData> itemList) {
        this.orderId = orderId;
        this.itemList = itemList;
    }
}
