package org.example.food.dtos.testimonialdto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestimonialUpdateDto {
    private Long id;
    private String fullName;
    private String job;
    private String thoughts;
    private int rating;
    private String photoUrl;
    private MultipartFile photoFile;
}
