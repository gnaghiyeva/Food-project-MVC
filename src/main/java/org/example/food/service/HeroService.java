package org.example.food.service;

import org.example.food.dtos.herodtos.HeroCreateDto;
import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.dtos.herodtos.HeroUpdateDto;
import org.example.food.payloads.APIResponse;

import java.util.List;

public interface HeroService {
    void createHero(HeroCreateDto heroCreateDto);
    List<HeroDto> getHero();
    void updatedHero(HeroUpdateDto heroUpdateDto);
    HeroUpdateDto findUpdateHero(Long id);
}
