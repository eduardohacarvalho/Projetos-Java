package com.jpa_spring.jpa_spring.repositories;

import com.jpa_spring.jpa_spring.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
