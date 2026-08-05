package org.example.food.repository;

import org.example.food.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<Chef,Integer> {
}
