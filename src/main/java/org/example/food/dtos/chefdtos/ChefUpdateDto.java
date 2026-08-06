package org.example.food.dtos.chefdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ChefUpdateDto {
    private Long id;
    private String fullName;
    private String position;
    private String description;
    private String photoUrl;
    private MultipartFile photoFile;
    private String xLink;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
}
