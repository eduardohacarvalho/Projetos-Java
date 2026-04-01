package com.jpa_spring.jpa_spring.repositories;

import com.jpa_spring.jpa_spring.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
