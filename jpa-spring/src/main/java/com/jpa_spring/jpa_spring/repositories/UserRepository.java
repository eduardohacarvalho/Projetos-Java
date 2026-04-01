package com.jpa_spring.jpa_spring.repositories;

import com.jpa_spring.jpa_spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
