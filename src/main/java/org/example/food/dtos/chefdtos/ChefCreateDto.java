package org.example.food.dtos.chefdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class ChefCreateDto {
    private String fullName;
    private String position;
    private String description;
    private MultipartFile photoFile;
    private String xLink;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
    private Date createdAt;
}
