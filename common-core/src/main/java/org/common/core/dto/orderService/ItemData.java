package org.common.core.dto.orderService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ItemData implements Serializable {
    private Integer id;
    private Integer quantity;
    private String itemName;

    public ItemData() {
    }

    public ItemData(Integer id, Integer quantity, String itemName) {
        this.id = id;
        this.quantity = quantity;
        this.itemName = itemName;
    }
}
