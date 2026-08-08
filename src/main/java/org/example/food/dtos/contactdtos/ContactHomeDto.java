package org.example.food.dtos.contactdtos;

import lombok.Data;

@Data
public class ContactHomeDto {
    private String location;
    private String address;
    private String phone;
    private String email;
    private String openingHour;

//    private String name;
//    private String surname;
//    private String subject;
//    private String thoughts;

    private String xLink;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
}
