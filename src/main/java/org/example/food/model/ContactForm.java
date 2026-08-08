package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contactform")
@Data
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String thoughts;
}
