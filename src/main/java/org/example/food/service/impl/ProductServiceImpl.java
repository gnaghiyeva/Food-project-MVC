package org.example.food.service.impl;

import jakarta.transaction.Transactional;
import org.example.food.dtos.productdtos.ProductCreateDto;
import org.example.food.dtos.productdtos.ProductDto;
import org.example.food.dtos.productdtos.ProductUpdateDto;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> products = productRepository.findAll().stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        return products;
    }

    @Override
    public void removeProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();

        String photoUrl = product.getPhotoUrl();
        if (photoUrl != null && !photoUrl.isEmpty()) {

            String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);

            // 2. UPLOAD_DIR yolundan sil
            File imageFile = new File(UPLOAD_DIR + fileName);
            if (imageFile.exists()) {
                if (imageFile.delete()) {
                    System.out.println("Photo deleted from UPLOAD_DIR: " + imageFile.getPath());
                } else {
                    System.out.println("Photo can not deleted from UPLOAD_DIR: " + imageFile.getPath());
                }
            }

            // 3. STATIC_UPLOAD_DIR yolundan sil
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

        productRepository.delete(product);
    }

    @Override
    public void updateProduct(ProductUpdateDto productUpdateDto) {
        Product findProduct = productRepository.findById(productUpdateDto.getId()).orElseThrow();
        Category category = categoryRepository.findById(productUpdateDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        MultipartFile file = productUpdateDto.getPhotoFile();
        if (file != null && !file.isEmpty()) {
            try {
                // Köhnə şəkli sil
                String oldFileName = findProduct.getPhotoUrl();
                if (oldFileName != null && !oldFileName.isEmpty()) {
                    Path oldUploadPath = Paths.get(UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldUploadPath);

                    Path oldStaticPath = Paths.get(STATIC_UPLOAD_DIR + oldFileName);
                    Files.deleteIfExists(oldStaticPath);
                }

                // Yeni şəkli yüklə
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

                // Yalnız yeni şəkil varsa, photoUrl yenilə
                findProduct.setPhotoUrl(uniqueFileName);

            } catch (IOException e) {
                System.err.println("Foto yenilənərkən xəta baş verdi: " + e.getMessage());
                return;
            }
        }
// Əks halda photoUrl olduğu kimi saxlanacaq


        findProduct.setId(productUpdateDto.getId());
        findProduct.setName(productUpdateDto.getName());
        findProduct.setIngredient(productUpdateDto.getIngredient());
        findProduct.setPrice(Long.valueOf(productUpdateDto.getPrice()));
        findProduct.setCategory(category);
        findProduct.setUpdatedDate(new Date());
        productRepository.saveAndFlush(findProduct);
    }

    @Override
    public ProductUpdateDto findUpdatedProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        ProductUpdateDto productUpdateDto = modelMapper.map(product, ProductUpdateDto.class);
        return productUpdateDto;
    }

}
