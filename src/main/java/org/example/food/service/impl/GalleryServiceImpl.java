package org.example.food.service.impl;

import org.example.food.dtos.eventdtos.EventDto;
import org.example.food.dtos.eventdtos.EventHomeDto;
import org.example.food.dtos.gallerydtos.GalleryCreateDto;
import org.example.food.dtos.gallerydtos.GalleryDto;
import org.example.food.dtos.gallerydtos.GalleryHomeDto;
import org.example.food.dtos.gallerydtos.GalleryUpdateDto;
import org.example.food.mapper.GalleryMapper;
import org.example.food.model.Event;
import org.example.food.model.Gallery;
import org.example.food.repository.GalleryRepository;
import org.example.food.service.GalleryService;
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

public class GalleryServiceImpl implements GalleryService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String STATIC_UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private GalleryMapper galleryMapper;


    @Override
    public void addPhoto(GalleryCreateDto galleryCreateDto) {
        Gallery gallery = galleryMapper.toEntity(galleryCreateDto);
        MultipartFile file = galleryCreateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                if (gallery.getPhotoUrl() != null) {
                    String oldFileName = gallery.getPhotoUrl();

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

                gallery.setPhotoUrl(uniqueFileName);
            } catch (IOException e) {
                System.err.println("Photo upload failed: " + e.getMessage());
                return;
            }
        } else {
            System.err.println("Photo file cannot be empty!");
            return;
        }

        galleryRepository.save(gallery);
    }

    @Override
    public List<GalleryDto> getGallery() {
        List<GalleryDto> galleries = galleryRepository.findAll().stream()
                .map(galleryMapper::toDto)
                .collect(Collectors.toList());
        return galleries;
    }

    @Override
    public void removePhoto(int id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow();
        String photoUrl = gallery.getPhotoUrl();
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

        galleryRepository.delete(gallery);
    }

    @Override
    public void updatePhoto(GalleryUpdateDto galleryUpdateDto) {
        Gallery findEvent = galleryRepository.findById(Math.toIntExact(galleryUpdateDto.getId())).orElseThrow();
        MultipartFile file = galleryUpdateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                String oldFileName = findEvent.getPhotoUrl();
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

                findEvent.setPhotoUrl(uniqueFileName);

            } catch (IOException e) {
                System.err.println("Error occured while photo updated: " + e.getMessage());
                return;
            }
        }
        // fullName/job/thoughts/rating-ı mapper ilə yeniləyirik; photoUrl mapperdə ignore olunub
        galleryMapper.updateEntityFromDto(galleryUpdateDto, findEvent);
        galleryRepository.saveAndFlush(findEvent);
    }

    @Override
    public GalleryUpdateDto findUpdatedPhoto(int id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow();
        return galleryMapper.toUpdateDto(gallery);
    }

    @Override
    public List<GalleryHomeDto> getHomeGallery() {
        List<GalleryHomeDto> galleryHomeDtos = galleryRepository.findAll().stream()
                .map(galleryMapper::toHomeDto)
                .collect(Collectors.toList());
        return galleryHomeDtos;
    }
}
