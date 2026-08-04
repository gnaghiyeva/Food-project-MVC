package org.example.food.service.impl;

import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.dtos.whyusdtos.WhyUsCreateDto;
import org.example.food.dtos.whyusdtos.WhyUsDto;
import org.example.food.dtos.whyusdtos.WhyUsHomeDto;
import org.example.food.dtos.whyusdtos.WhyUsUpdateDto;
import org.example.food.mapper.WhyUsMapper;
import org.example.food.model.WhyUs;
import org.example.food.repository.WhyUsRepository;
import org.example.food.service.WhyUsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WhyUsServiceImpl implements WhyUsService {
    @Autowired
    private WhyUsRepository whyUsRepository;

    @Autowired
    private WhyUsMapper whyUsMapper;

    @Override
    public void createCard(WhyUsCreateDto whyUsCreateDto) {
        // isMain null gələrsə mapperdə false-a çevrilir (əvvəlki davranışla eyni)
        WhyUs card = whyUsMapper.toEntity(whyUsCreateDto);
        whyUsRepository.save(card);
    }

    @Override
    public List<WhyUsDto> getCards() {
        List<WhyUsDto> result = whyUsRepository.findAll().stream()
                .map(whyUsMapper::toDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void updatedCard(WhyUsUpdateDto whyUsUpdateDto) {
        if (whyUsUpdateDto == null || whyUsUpdateDto.getId() == null) {
            throw new IllegalArgumentException("Card or Card ID cannot be null");
        }

        WhyUs findCard = whyUsRepository.findById(whyUsUpdateDto.getId()).orElseThrow();
        whyUsMapper.updateEntityFromDto(whyUsUpdateDto, findCard);

        whyUsRepository.saveAndFlush(findCard);
    }

    @Override
    public WhyUsUpdateDto findUpdatedCard(Long id) {
        WhyUs card = whyUsRepository.findById(id).orElseThrow();
        return whyUsMapper.toUpdateDto(card);
    }

    @Override
    public void removeCard(Long id) {
        WhyUs card = whyUsRepository.findById(id).orElseThrow();
        whyUsRepository.delete(card);
    }

    @Override
    public List<WhyUsHomeDto> getHomeCards() {
        List<WhyUsHomeDto> allCards = whyUsRepository.findAll().stream()
                .map(whyUsMapper::toHomeDto)
                .collect(Collectors.toList());
        return allCards;
    }
}
