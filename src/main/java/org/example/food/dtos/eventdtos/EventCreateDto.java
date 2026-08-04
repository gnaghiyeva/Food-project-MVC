package org.example.food.dtos.eventdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EventCreateDto {
    private String title;
    private String subTitle;
    private int price;
    private MultipartFile photoFile;
}

