package org.example.food.service;

import org.example.food.dtos.whyusdtos.WhyUsCreateDto;
import org.example.food.dtos.whyusdtos.WhyUsDto;
import org.example.food.dtos.whyusdtos.WhyUsUpdateDto;

import java.util.List;

public interface WhyUsService {
    void createCard(WhyUsCreateDto whyUsCreateDto);
    List<WhyUsDto> getCards();
    void updatedCard(WhyUsUpdateDto whyUsUpdateDto);
    WhyUsUpdateDto findUpdatedCard(Long id);
    void removeCard(Long id);
}
