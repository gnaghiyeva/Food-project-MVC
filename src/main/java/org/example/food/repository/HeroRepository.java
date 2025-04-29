package org.example.food.repository;

import org.example.food.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero,Long> {
}
