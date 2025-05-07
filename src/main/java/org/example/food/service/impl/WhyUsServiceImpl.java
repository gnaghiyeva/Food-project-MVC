package org.example.food.service.impl;

import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.dtos.whyusdtos.WhyUsCreateDto;
import org.example.food.dtos.whyusdtos.WhyUsDto;
import org.example.food.dtos.whyusdtos.WhyUsHomeDto;
import org.example.food.dtos.whyusdtos.WhyUsUpdateDto;
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
    private ModelMapper modelMapper;

    @Override
    public void createCard(WhyUsCreateDto whyUsCreateDto) {
//        WhyUs card = modelMapper.map(whyUsCreateDto, WhyUs.class);
//        whyUsRepository.save(card);

        WhyUs card = new WhyUs();
        card.setTitle(whyUsCreateDto.getTitle());
        card.setSubTitle(whyUsCreateDto.getSubTitle());
        card.setIcon(whyUsCreateDto.getIcon());
        card.setIsMain(whyUsCreateDto.getIsMain() != null && whyUsCreateDto.getIsMain()); // NULL deyilsə və TRUE-dursa, TRUE ver

        System.out.println("Mapped isMain to entity: " + card.getIsMain()); // bu TRUE olmalıdır

        whyUsRepository.save(card);
    }

    @Override
    public List<WhyUsDto> getCards() {
        List<WhyUsDto> result = whyUsRepository.findAll().stream().map(hero -> modelMapper.map(hero, WhyUsDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void updatedCard(WhyUsUpdateDto whyUsUpdateDto) {
        if(whyUsUpdateDto == null || whyUsUpdateDto.getId() == null){
            throw new IllegalArgumentException("Card or Card ID cannot be null");
        }

        WhyUs findCard = whyUsRepository.findById(whyUsUpdateDto.getId()).orElseThrow();
        findCard.setTitle(whyUsUpdateDto.getTitle());
        findCard.setSubTitle(whyUsUpdateDto.getSubTitle());
        findCard.setIcon(whyUsUpdateDto.getIcon());
        findCard.setIsMain(whyUsUpdateDto.getIsMain());

        whyUsRepository.saveAndFlush(findCard);
    }

    @Override
    public WhyUsUpdateDto findUpdatedCard(Long id) {
        WhyUs card = whyUsRepository.findById(id).orElseThrow();
        WhyUsUpdateDto whyUsUpdateDto = modelMapper.map(card, WhyUsUpdateDto.class);
        return whyUsUpdateDto;
    }

    @Override
    public void removeCard(Long id) {
        WhyUs card = whyUsRepository.findById(id).orElseThrow();
        whyUsRepository.delete(card);
    }

    @Override
    public List<WhyUsHomeDto> getHomeCards() {
        List<WhyUsHomeDto> allCards = whyUsRepository.findAll().stream().map(card->modelMapper.map(card, WhyUsHomeDto.class)).collect(Collectors.toList());
        return allCards;
    }
}
