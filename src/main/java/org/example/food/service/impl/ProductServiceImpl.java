package org.example.food.service.impl;

import jakarta.transaction.Transactional;
import org.example.food.dtos.productdtos.ProductCreateDto;
import org.example.food.model.Category;
import org.example.food.model.Product;
import org.example.food.repository.CategoryRepository;
import org.example.food.repository.ProductRepository;
import org.example.food.service.ProductService;
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
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String STATIC_UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void addProduct(ProductCreateDto productCreateDto) {
        Product product = modelMapper.map(productCreateDto,Product.class);
        product.setId(null);
        Category category = categoryRepository.findById(productCreateDto.getCategoryId()).get();

        product.setCategory(category);
        MultipartFile file = productCreateDto.getPhotoFile();
        if(file != null && !file.isEmpty()){
            try {
                if(product.getPhotoUrl() != null){
                    String oldFileName = product.getPhotoUrl();

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

                product.setPhotoUrl(uniqueFileName);
            }catch (IOException e){
                System.err.println("Photo upload failed: " + e.getMessage());
                return;
            }
        }
        else {
            System.err.println("Photo file cannot be empty!");
            return;
        }

        product.setCreatedDate(new Date());
        product.setUpdatedDate(new Date());
        productRepository.saveAndFlush(product);

    }
}
