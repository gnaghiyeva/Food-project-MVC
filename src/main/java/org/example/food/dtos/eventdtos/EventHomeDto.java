package org.example.food.dtos.eventdtos;

import lombok.Data;

@Data
public class EventHomeDto {
    private Long id;
    private String title;
    private String subTitle;
    private String photoUrl;
    private int price;
}

