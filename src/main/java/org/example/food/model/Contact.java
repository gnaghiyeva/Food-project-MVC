package org.example.food.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contact")
@Data
public class Contact {
    @Id
    private Long id=1L;
    private String location;
    private String address;
    private String phone;
    private String email;
    private String openingHour;



    private String xLink;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
}
