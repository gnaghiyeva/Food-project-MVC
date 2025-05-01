package org.example.food.service.impl;

import jdk.jfr.Category;
import org.example.food.dtos.herodtos.HeroCreateDto;
import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.dtos.herodtos.HeroHomeDto;
import org.example.food.dtos.herodtos.HeroUpdateDto;
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
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Service
public class HeroServiceImpl implements HeroService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String STATIC_UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

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
                if (hero.getPhotoUrl() != null) {
                    String oldFileName = hero.getPhotoUrl();

                    // Hem project root/uploads içinden sil
                    Path oldFilePath = Paths.get(UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldFilePath);

                    // Hem static/uploads içinden sil
                    Path oldStaticPath = Paths.get(STATIC_UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldStaticPath);
                }

                // 2. Yeni file yaratmaq
                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

                // uploads/ folderine yaz
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
                Files.write(filePath, file.getBytes());

                // static/uploads/ folderine kopyala
                File staticUploadDir = new File(STATIC_UPLOAD_DIR);
                if (!staticUploadDir.exists()) staticUploadDir.mkdirs();
                Path staticFilePath = Paths.get(STATIC_UPLOAD_DIR + uniqueFileName);
                Files.copy(filePath, staticFilePath, StandardCopyOption.REPLACE_EXISTING);

                hero.setPhotoUrl(uniqueFileName);
            } catch (IOException e) {
                System.err.println("Photo upload failed: " + e.getMessage());
                return;
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

    @Override
    public void updatedHero(HeroUpdateDto heroDto) {
        Hero findHero = heroRepository.findById(heroDto.getId()).orElseThrow();
        MultipartFile file = heroDto.getPhotoFile();

        if (file != null && !file.isEmpty()) {
            try {
                // Əvvəlki şəkilləri silmək
                String oldFileName = findHero.getPhotoUrl();
                if (oldFileName != null && !oldFileName.isEmpty()) {
                    Path oldFilePath = Paths.get(UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldFilePath);

                    Path oldStaticPath = Paths.get(STATIC_UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldStaticPath);
                }

                // Yeni faylın adınının təyin olunması
                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

                // uploads/ folderinə yaz
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
                Files.write(filePath, file.getBytes());

                // static/uploads/ folderinə kopyala
                File staticUploadDir = new File(STATIC_UPLOAD_DIR);
                if (!staticUploadDir.exists()) staticUploadDir.mkdirs();
                Path staticFilePath = Paths.get(STATIC_UPLOAD_DIR + uniqueFileName);
                Files.copy(filePath, staticFilePath, StandardCopyOption.REPLACE_EXISTING);

                findHero.setPhotoUrl(uniqueFileName);

            } catch (IOException e) {
                System.err.println("Photo update failed: " + e.getMessage());
            }
        }

        findHero.setTitle(heroDto.getTitle());
        findHero.setSubTitle(heroDto.getSubTitle());
        findHero.setVideoUrl(heroDto.getVideoUrl());

        heroRepository.saveAndFlush(findHero);
    }


    @Override
    public HeroUpdateDto findUpdateHero(Long id) {
        Hero hero = heroRepository.findById(id).orElseThrow();
        HeroUpdateDto heroUpdateDto = modelMapper.map(hero, HeroUpdateDto.class);
        return heroUpdateDto;
    }

    @Override
    public List<HeroHomeDto> getHomeHero() {
        List<HeroHomeDto>heroDto = heroRepository.findAll().stream()
                .map(article -> modelMapper.map(article, HeroHomeDto.class))
                .collect(Collectors.toList());
        return heroDto;
    }

}
