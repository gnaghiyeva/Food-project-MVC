package org.example.food.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "about")
@Data
public class About {
    @Id
    private Long id=1L;
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    private String videoUrl;
    private String photoUrl;
}
