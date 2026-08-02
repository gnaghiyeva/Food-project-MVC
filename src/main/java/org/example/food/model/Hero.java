package org.example.food.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hero")
@Getter
@Setter
public class Hero {
    @Id
    private Long id = 1L;
    String title;
    String subTitle;
    String videoUrl;
    String photoUrl;
}
