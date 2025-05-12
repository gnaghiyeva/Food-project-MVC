package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "testimonial")
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String job;
    private String thoughts;
    private int rating;
    private String photoUrl;
}
