package org.example.food.service;

import org.example.food.dtos.productdtos.ProductHomeDto;
import org.example.food.dtos.testimonialdto.TestimonialCreateDto;
import org.example.food.dtos.testimonialdto.TestimonialDto;
import org.example.food.dtos.testimonialdto.TestimonialHomeDto;
import org.example.food.dtos.testimonialdto.TestimonialUpdateDto;

import java.util.List;

public interface TestimonialService {
    void addTestimonial(TestimonialCreateDto testimonialCreateDto);
    List<TestimonialDto> getTestimonial();
    void updateTestimonial(TestimonialUpdateDto testimonialUpdateDto);
    TestimonialUpdateDto findUpdatedTestimonial(Long id);
    void removeTestimonial(Long id);
    List<TestimonialHomeDto> getHomeTestimonials();
}
