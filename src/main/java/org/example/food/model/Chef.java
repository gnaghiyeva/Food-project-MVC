package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "chef")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fullName;
    private String position;
    private String description;
    private String photoUrl;
    private String xLink;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
    private Date createdAt;
}
