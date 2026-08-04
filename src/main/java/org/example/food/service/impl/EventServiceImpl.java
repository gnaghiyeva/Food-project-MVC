package org.example.food.service.impl;


import jakarta.transaction.Transactional;
import org.example.food.dtos.eventdtos.EventCreateDto;
import org.example.food.dtos.eventdtos.EventDto;
import org.example.food.dtos.eventdtos.EventHomeDto;
import org.example.food.dtos.eventdtos.EventUpdateDto;
import org.example.food.mapper.EventMapper;
import org.example.food.model.Event;
import org.example.food.repository.EventRepository;
import org.example.food.service.EventService;
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
@Transactional
public class EventServiceImpl implements EventService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String STATIC_UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Override
    public void addEvent(EventCreateDto eventCreateDto) {
        Event event = eventMapper.toEntity(eventCreateDto);
        MultipartFile file = eventCreateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                if (event.getPhotoUrl() != null) {
                    String oldFileName = event.getPhotoUrl();

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

                event.setPhotoUrl(uniqueFileName);
            } catch (IOException e) {
                System.err.println("Photo upload failed: " + e.getMessage());
                return;
            }
        } else {
            System.err.println("Photo file cannot be empty!");
            return;
        }

        eventRepository.save(event);
    }

    @Override
    public List<EventDto> getEvents() {
        List<EventDto> events = eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
        return events;
    }

    @Override
    public void removeEvent(int id) {
        Event event = eventRepository.findById(id).orElseThrow();
        String photoUrl = event.getPhotoUrl();
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

        eventRepository.delete(event);
    }

    @Override
    public void updateEvent(EventUpdateDto eventUpdateDto) {
        Event findEvent = eventRepository.findById(Math.toIntExact(eventUpdateDto.getId())).orElseThrow();
        MultipartFile file = eventUpdateDto.getPhotoFile();
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
        eventMapper.updateEntityFromDto(eventUpdateDto, findEvent);
        eventRepository.saveAndFlush(findEvent);
    }

    @Override
    public EventUpdateDto findUpdatedEvent(int id) {
        Event event = eventRepository.findById(id).orElseThrow();
        return eventMapper.toUpdateDto(event);
    }

    @Override
    public List<EventHomeDto> getHomeEvents() {
        List<EventHomeDto> eventHomeDtos = eventRepository.findAll().stream()
                .map(eventMapper::toHomeDto)
                .collect(Collectors.toList());
        return eventHomeDtos;
    }
}

