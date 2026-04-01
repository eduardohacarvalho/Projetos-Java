package com.jpa_spring.jpa_spring.repositories;

import com.jpa_spring.jpa_spring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
