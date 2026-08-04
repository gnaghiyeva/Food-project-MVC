package org.example.food.mapper;

import org.example.food.dtos.productdtos.ProductCreateDto;
import org.example.food.dtos.productdtos.ProductDto;
import org.example.food.dtos.productdtos.ProductHomeDto;
import org.example.food.dtos.productdtos.ProductUpdateDto;
import org.example.food.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    // category, categoryId ilə repository-dən tapılıb service qatında set olunur
    // createdDate/updatedDate də service qatında set olunur
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Product toEntity(ProductCreateDto productCreateDto);

    // Category -> CategoryDto çevrilməsi avtomatik olaraq CategoryMapper vasitəsilə edilir
    ProductDto toDto(Product product);

    ProductHomeDto toHomeDto(Product product);

    @Mapping(target = "photoFile", ignore = true)
    @Mapping(target = "categoryId", source = "category.id")
        // Product.price -> Long, ProductUpdateDto.price -> String olduğu üçün MapStruct avtomatik çevirir
    ProductUpdateDto toUpdateDto(Product product);

    @Mapping(target = "photoUrl", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    void updateEntityFromDto(ProductUpdateDto productUpdateDto, @MappingTarget Product product);
}