package org.common.core.dto.itemService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class ItemDto {
    private int id;
    private String itemName;
    private String itemDescription;
    private String category;
    private java.sql.Date createdAt;
    private java.sql.Date updatedAt;
}
