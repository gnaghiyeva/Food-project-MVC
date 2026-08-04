package org.example.food.dtos.eventdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EventUpdateDto {
    private Long id;
    private String title;
    private String price;
    private String subTitle;
    private String photoUrl;
    private MultipartFile photoFile;
}
