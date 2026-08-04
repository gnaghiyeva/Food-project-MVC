package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String subTitle;
    private int price;
    private String photoUrl;

}
