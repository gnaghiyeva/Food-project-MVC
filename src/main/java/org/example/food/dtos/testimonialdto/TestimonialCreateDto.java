package org.example.food.dtos.testimonialdto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestimonialCreateDto {
    private String fullName;
    private String job;
    private String thoughts;
    private int rating;
    private MultipartFile photoFile;
}
