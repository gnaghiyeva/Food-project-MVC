package org.example.food.service;

import org.example.food.dtos.testimonialdto.TestimonialCreateDto;
import org.example.food.dtos.testimonialdto.TestimonialDto;

import java.util.List;

public interface TestimonialService {
    void addTestimonial(TestimonialCreateDto testimonialCreateDto);
    List<TestimonialDto> getTestimonial();
}
