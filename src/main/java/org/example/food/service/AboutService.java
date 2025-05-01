package org.example.food.service;

import org.example.food.dtos.aboutdtos.AboutCreateDto;
import org.example.food.dtos.aboutdtos.AboutDto;

import java.util.List;

public interface AboutService {
    void createAbout(AboutCreateDto aboutCreateDto);
    List<AboutDto> getAbout();

}
