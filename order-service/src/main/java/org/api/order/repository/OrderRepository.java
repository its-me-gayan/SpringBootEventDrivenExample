package org.api.order.repository;

import org.api.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order ,Integer> {

}
