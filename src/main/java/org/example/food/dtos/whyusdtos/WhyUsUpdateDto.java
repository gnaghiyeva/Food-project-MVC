package org.example.food.dtos.whyusdtos;

import lombok.Data;

@Data
public class WhyUsUpdateDto {
    private Long id;
    private String title;
    private String subTitle;
    private String icon;
    private Boolean isMain;
}
