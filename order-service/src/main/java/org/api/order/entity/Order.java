package org.api.order.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.core.enums.OrderStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;

@Data
@Entity
@Table(name = "order_")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "totalAmount")
    private Double totalAmount;
    @Column(name = "customerId")
    private Integer customerId;
    @Column(name = "itemCount")
    private Integer itemCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "createdAt")
    @CreatedDate
    private java.sql.Date createdAt;
    @Column(name = "updatedAt")
    @LastModifiedDate
    private java.sql.Date updatedAt;
}
