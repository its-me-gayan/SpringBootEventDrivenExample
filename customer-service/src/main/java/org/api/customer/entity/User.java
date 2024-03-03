package org.api.customer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstName")
    private String firstName;


    @Column(name = "lastName")
    private String lastName;

    @Column(name = "userRole")
    private String userRole;

    @Column(name = "createdAt")
    @CreatedDate
    private java.sql.Date createdAt;
    @Column(name = "updatedAt")
    @LastModifiedDate
    private java.sql.Date updatedAt;



}
