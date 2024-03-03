package org.api.packinglot.entity;


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
@Table(name = "event_store")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Lob
    @Column(name = "payload",columnDefinition = "text")
    private String payload;

    @Lob
    @Column(name = "header_value",columnDefinition = "text")
    private String header_value;

    @Column(name = "org_queue")
    private String org_queue;

    @Column(name = "org_exchange")
    private String org_exchange;


    @Column(name = "org_routing_key")
    private String org_routing_key;

    @Column(name = "createdAt")
    @CreatedDate
    private java.sql.Date createdAt;

    @Column(name = "updatedAt")
    @LastModifiedDate
    private java.sql.Date updatedAt;
}
