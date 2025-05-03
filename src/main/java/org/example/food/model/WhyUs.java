package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "whyUs")
public class WhyUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subTitle;
    private String icon;
//    @Column(name = "isMain", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isMain;
}
