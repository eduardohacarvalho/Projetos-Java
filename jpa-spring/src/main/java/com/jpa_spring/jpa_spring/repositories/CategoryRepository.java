package com.jpa_spring.jpa_spring.repositories;

import com.jpa_spring.jpa_spring.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
