package org.example.food.service.impl;

import jdk.jfr.Category;
import org.example.food.dtos.herodtos.HeroCreateDto;
import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.model.Hero;
import org.example.food.payloads.APIResponse;
import org.example.food.repository.HeroRepository;
import org.example.food.service.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Service
public class HeroServiceImpl implements HeroService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createHero(HeroCreateDto heroCreateDto) {
        Hero hero = modelMapper.map(heroCreateDto, Hero.class);
        MultipartFile file = heroCreateDto.getPhotoFile();

        if (file != null && !file.isEmpty()) {
            try {
                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
                Files.write(filePath, file.getBytes());

                hero.setPhotoUrl(uniqueFileName);

            } catch (IOException e) {
                System.err.println("Photo upload failed: " + e.getMessage());
                return; // və ya istəyə görə exception throw oluna bilər
            }
        } else {
            System.err.println("Photo file cannot be empty!");
            return;
        }

        heroRepository.save(hero);
    }

    @Override
    public List<HeroDto> getHero() {
        List<HeroDto> result = heroRepository.findAll().stream().map(hero -> modelMapper.map(hero, HeroDto.class))
                .collect(Collectors.toList());
        return result;
    }

}
