package org.example.food.mapper;


import org.example.food.dtos.testimonialdto.TestimonialCreateDto;
import org.example.food.dtos.testimonialdto.TestimonialDto;
import org.example.food.dtos.testimonialdto.TestimonialHomeDto;
import org.example.food.dtos.testimonialdto.TestimonialUpdateDto;
import org.example.food.model.Testimonial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TestimonialMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    Testimonial toEntity(TestimonialCreateDto testimonialCreateDto);

    TestimonialDto toDto(Testimonial testimonial);

    TestimonialHomeDto toHomeDto(Testimonial testimonial);

    @Mapping(target = "photoFile", ignore = true)
    TestimonialUpdateDto toUpdateDto(Testimonial testimonial);

    @Mapping(target = "photoUrl", ignore = true)
    void updateEntityFromDto(TestimonialUpdateDto testimonialUpdateDto, @MappingTarget Testimonial testimonial);
}
