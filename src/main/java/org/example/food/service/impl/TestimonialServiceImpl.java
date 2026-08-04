package org.example.food.service.impl;

import org.example.food.dtos.productdtos.ProductHomeDto;
import org.example.food.dtos.testimonialdto.TestimonialCreateDto;
import org.example.food.dtos.testimonialdto.TestimonialDto;
import org.example.food.dtos.testimonialdto.TestimonialHomeDto;
import org.example.food.dtos.testimonialdto.TestimonialUpdateDto;
import org.example.food.mapper.TestimonialMapper;
import org.example.food.model.Testimonial;
import org.example.food.repository.TestimonialRepository;
import org.example.food.service.TestimonialService;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TestimonialServiceImpl implements TestimonialService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String STATIC_UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private TestimonialMapper testimonialMapper;

    @Override
    public void addTestimonial(TestimonialCreateDto testimonialCreateDto) {
        Testimonial testimonial = testimonialMapper.toEntity(testimonialCreateDto);
        MultipartFile file = testimonialCreateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                if (testimonial.getPhotoUrl() != null) {
                    String oldFileName = testimonial.getPhotoUrl();

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

                testimonial.setPhotoUrl(uniqueFileName);
            } catch (IOException e) {
                System.err.println("Photo upload failed: " + e.getMessage());
                return;
            }
        } else {
            System.err.println("Photo file cannot be empty!");
            return;
        }

        testimonialRepository.save(testimonial);
    }

    @Override
    public List<TestimonialDto> getTestimonial() {
        List<TestimonialDto> testimonials = testimonialRepository.findAll().stream()
                .map(testimonialMapper::toDto)
                .collect(Collectors.toList());
        return testimonials;
    }

    @Override
    public void updateTestimonial(TestimonialUpdateDto testimonialUpdateDto) {
        Testimonial findTestimonial = testimonialRepository.findById(testimonialUpdateDto.getId()).orElseThrow();
        MultipartFile file = testimonialUpdateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                String oldFileName = findTestimonial.getPhotoUrl();
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

                findTestimonial.setPhotoUrl(uniqueFileName);

            } catch (IOException e) {
                System.err.println("Error occured while photo updated: " + e.getMessage());
                return;
            }
        }
        // fullName/job/thoughts/rating-ı mapper ilə yeniləyirik; photoUrl mapperdə ignore olunub
        testimonialMapper.updateEntityFromDto(testimonialUpdateDto, findTestimonial);
        testimonialRepository.saveAndFlush(findTestimonial);
    }

    @Override
    public TestimonialUpdateDto findUpdatedTestimonial(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow();
        return testimonialMapper.toUpdateDto(testimonial);
    }

    @Override
    public void removeTestimonial(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow();
        String photoUrl = testimonial.getPhotoUrl();
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

        testimonialRepository.delete(testimonial);
    }

    @Override
    public List<TestimonialHomeDto> getHomeTestimonials() {
        List<TestimonialHomeDto> testimonialHomeDtos = testimonialRepository.findAll().stream()
                .map(testimonialMapper::toHomeDto)
                .collect(Collectors.toList());
        return testimonialHomeDtos;
    }
}
