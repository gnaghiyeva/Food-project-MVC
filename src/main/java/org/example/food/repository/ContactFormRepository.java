package org.example.food.repository;

import org.example.food.model.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm,Long> {
}
