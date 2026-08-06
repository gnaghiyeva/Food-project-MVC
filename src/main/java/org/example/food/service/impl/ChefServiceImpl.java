package org.example.food.service.impl;

import org.example.food.dtos.chefdtos.ChefCreateDto;
import org.example.food.dtos.chefdtos.ChefDto;
import org.example.food.dtos.chefdtos.ChefHomeDto;
import org.example.food.dtos.chefdtos.ChefUpdateDto;
import org.example.food.mapper.ChefMapper;
import org.example.food.model.Chef;
import org.example.food.model.Product;
import org.example.food.repository.ChefRepository;
import org.example.food.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class ChefServiceImpl implements ChefService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String STATIC_UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private ChefMapper chefMapper;

    @Override
    public void addChef(ChefCreateDto chefCreateDto) {
        Chef chef = chefMapper.toEntity(chefCreateDto);
        MultipartFile file = chefCreateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                if (chef.getPhotoUrl() != null) {
                    String oldFileName = chef.getPhotoUrl();

                    Path oldFilePath = Paths.get(UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldFilePath);

                    Path oldStaticPath = Paths.get(STATIC_UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldStaticPath);
                }

                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
                Files.write(filePath, file.getBytes());

                File staticUploadDir = new File(STATIC_UPLOAD_DIR);
                if (!staticUploadDir.exists()) staticUploadDir.mkdirs();
                Path staticFilePath = Paths.get(STATIC_UPLOAD_DIR + uniqueFileName);
                Files.copy(filePath, staticFilePath, StandardCopyOption.REPLACE_EXISTING);

                chef.setPhotoUrl(uniqueFileName);
            } catch (IOException e) {
                System.err.println("Photo upload failed: " + e.getMessage());
                return;
            }
        } else {
            System.err.println("Photo file cannot be empty!");
            return;
        }

        chef.setCreatedAt(new Date());
        chefRepository.save(chef);
    }

    @Override
    public List<ChefDto> getChefs() {
        List<ChefDto> chefs = chefRepository.findAll().stream()
                .map(chefMapper::toDto)
                .collect(Collectors.toList());
        return chefs;
    }

    @Override
    public List<ChefHomeDto> getHomeChefs() {
        List<ChefHomeDto> chefHomeDtos = chefRepository.findAll().stream()
                .map(chefMapper::toHomeDto)
                .collect(Collectors.toList());
        return chefHomeDtos;
    }

    @Override
    public void updateChef(ChefUpdateDto chefUpdateDto) {
        Chef findChef = chefRepository.findById(Math.toIntExact(chefUpdateDto.getId())).orElseThrow();

        MultipartFile file = chefUpdateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                String oldFileName = findChef.getPhotoUrl();
                if (oldFileName != null && !oldFileName.isEmpty()) {
                    Path oldUploadPath = Paths.get(UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldUploadPath);

                    Path oldStaticPath = Paths.get(STATIC_UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldStaticPath);
                }

                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
                Files.write(filePath, file.getBytes());

                File staticUploadDir = new File(STATIC_UPLOAD_DIR);
                if (!staticUploadDir.exists()) staticUploadDir.mkdirs();
                Path staticFilePath = Paths.get(STATIC_UPLOAD_DIR + uniqueFileName);
                Files.copy(filePath, staticFilePath, StandardCopyOption.REPLACE_EXISTING);

                findChef.setPhotoUrl(uniqueFileName);

            } catch (IOException e) {
                System.err.println("Foto yenilənərkən xəta baş verdi: " + e.getMessage());
                return;
            }
        }

        chefMapper.updateEntityFromDto(chefUpdateDto, findChef);
        chefRepository.saveAndFlush(findChef);
    }

    @Override
    public ChefUpdateDto findUpdatedChef(int id) {
        Chef chef = chefRepository.findById(id).orElseThrow();
        return chefMapper.toUpdateDto(chef);
    }

    @Override
    public void removeChef(int id) {
        Chef chef = chefRepository.findById(id).orElseThrow();

        String photoUrl = chef.getPhotoUrl();
        if (photoUrl != null && !photoUrl.isEmpty()) {

            String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);

            File imageFile = new File(UPLOAD_DIR + fileName);
            if (imageFile.exists()) {
                if (imageFile.delete()) {
                    System.out.println("Photo deleted from UPLOAD_DIR: " + imageFile.getPath());
                } else {
                    System.out.println("Photo can not deleted from UPLOAD_DIR: " + imageFile.getPath());
                }
            }

            File staticImageFile = new File(STATIC_UPLOAD_DIR + fileName);
            if (staticImageFile.exists()) {
                if (staticImageFile.delete()) {
                    System.out.println("Photo deleted from STATIC_UPLOAD_DIR: " + staticImageFile.getPath());
                } else {
                    System.out.println("Photo can not deleted from STATIC_UPLOAD_DIR: " + staticImageFile.getPath());
                }
            }
        } else {
            System.out.println("Foto URL not exist or empty.");
        }

        chefRepository.delete(chef);
    }
}
