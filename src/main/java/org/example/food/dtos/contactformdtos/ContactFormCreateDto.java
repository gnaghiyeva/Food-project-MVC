package org.example.food.dtos.contactformdtos;

import lombok.Data;

@Data
public class ContactFormCreateDto {
    private String name;
    private String email;
    private String subject;
    private String thoughts;
}
