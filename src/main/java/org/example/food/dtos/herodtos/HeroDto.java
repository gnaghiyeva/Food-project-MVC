package org.example.food.dtos.herodtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroDto {
    private Long id;
    String title;
    String subTitle;
    String videoUrl;
    String photoUrl;
}
