package org.example.food.service;

import org.example.food.dtos.chefdtos.ChefCreateDto;
import org.example.food.dtos.chefdtos.ChefDto;
import org.example.food.dtos.chefdtos.ChefHomeDto;
import org.example.food.dtos.chefdtos.ChefUpdateDto;
import org.example.food.dtos.productdtos.ProductUpdateDto;

import java.util.List;

public interface ChefService {
    void addChef(ChefCreateDto chefCreateDto);
    List<ChefDto> getChefs();
    List<ChefHomeDto> getHomeChefs();
    void updateChef(ChefUpdateDto chefUpdateDto);
    ChefUpdateDto findUpdatedChef(int id);
    void removeChef(int id);
}
