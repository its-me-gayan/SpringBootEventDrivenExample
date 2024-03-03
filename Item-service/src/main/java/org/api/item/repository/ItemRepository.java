package org.api.item.repository;

import org.api.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item , Integer> {
}
