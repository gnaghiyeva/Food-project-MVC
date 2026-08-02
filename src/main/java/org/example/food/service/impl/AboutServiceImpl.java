package org.example.food.service.impl;

import org.example.food.dtos.aboutdtos.AboutCreateDto;
import org.example.food.dtos.aboutdtos.AboutDto;
import org.example.food.dtos.aboutdtos.AboutHomeDto;
import org.example.food.dtos.aboutdtos.AboutUpdateDto;
import org.example.food.model.About;
import org.example.food.repository.AboutRepository;
import org.example.food.service.AboutService;
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

@Service
public class AboutServiceImpl implements AboutService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String STATIC_UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Autowired
    private AboutRepository aboutRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createAbout(AboutCreateDto aboutCreateDto) {
        About about = modelMapper.map(aboutCreateDto, About.class);
        MultipartFile file = aboutCreateDto.getPhotoFile();

        if(file != null && !file.isEmpty()){
            try {
                if(about.getPhotoUrl() != null){
                    String oldFileName = about.getPhotoUrl();

                    Path oldFilePath = Paths.get(UPLOAD_DIR+oldFileName);
                    Files.deleteIfExists(oldFilePath);

                    Path oldStaticPath = Paths.get(STATIC_UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldStaticPath);
                }

                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String uniqueFileName = UUID.randomUUID().toString()+fileExtension;

                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
                Files.write(filePath, file.getBytes());

                File staticUploadDir = new File(STATIC_UPLOAD_DIR);
                if (!staticUploadDir.exists()) staticUploadDir.mkdirs();
                Path staticFilePath = Paths.get(STATIC_UPLOAD_DIR + uniqueFileName);
                Files.copy(filePath, staticFilePath, StandardCopyOption.REPLACE_EXISTING);

                about.setPhotoUrl(uniqueFileName);
            }catch (IOException e){
                System.err.println("Photo upload failed: " + e.getMessage());
                return;
            }
        }
        else {
            System.err.println("Photo file cannot be empty!");
            return;
        }

        aboutRepository.save(about);
    }

    @Override
    public List<AboutDto> getAbout() {
        List<AboutDto> result = aboutRepository.findAll().stream().map(about->modelMapper.map(about,AboutDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void updatedAbout(AboutUpdateDto aboutDto) {
        About findAbout = aboutRepository.findById(aboutDto.getId()).orElseThrow();
        MultipartFile file = aboutDto.getPhotoFile();

        if (file != null && !file.isEmpty()) {
            try {
                String oldFileName = findAbout.getPhotoUrl();
                if (oldFileName != null && !oldFileName.isEmpty()) {
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

                findAbout.setPhotoUrl(uniqueFileName);

            } catch (IOException e) {
                System.err.println("Photo update failed: " + e.getMessage());
            }
        }

        findAbout.setTitle(aboutDto.getTitle());
        findAbout.setDescription(aboutDto.getDescription());
        findAbout.setVideoUrl(aboutDto.getVideoUrl());

        aboutRepository.saveAndFlush(findAbout);

    }

    @Override
    public AboutUpdateDto findUpdatedAbout(Long id) {
        About about = aboutRepository.findById(id).orElseThrow();
        AboutUpdateDto aboutUpdateDto = modelMapper.map(about, AboutUpdateDto.class);
        return aboutUpdateDto;
    }

    @Override
    public List<AboutHomeDto> getHomeAbout() {
        List<AboutHomeDto> aboutDto = aboutRepository.findAll().stream()
                .map(about -> modelMapper.map(about, AboutHomeDto.class))
                .collect(Collectors.toList());
        return aboutDto;
    }
}
