package org.example.food.repository;

import org.example.food.model.About;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<About,Long> {
}
