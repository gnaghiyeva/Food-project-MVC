package org.example.food.service.impl;

import org.example.food.dtos.whyusdtos.WhyUsCreateDto;
import org.example.food.model.WhyUs;
import org.example.food.repository.WhyUsRepository;
import org.example.food.service.WhyUsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
