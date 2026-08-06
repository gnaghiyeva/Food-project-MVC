package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "gallery")
@Data
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String photoUrl;
}
