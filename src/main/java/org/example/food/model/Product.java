package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ingredient;
    private Long price;
    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
