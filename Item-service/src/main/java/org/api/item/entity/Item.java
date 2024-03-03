package org.api.item.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemDescription")
    private String itemDescription;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    @Column(name = "createdAt")
    @CreatedDate
    private java.sql.Date createdAt;
    @Column(name = "updatedAt")
    @LastModifiedDate
    private java.sql.Date updatedAt;
}
