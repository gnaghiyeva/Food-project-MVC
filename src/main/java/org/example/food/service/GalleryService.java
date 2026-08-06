package org.example.food.service;


import org.example.food.dtos.gallerydtos.GalleryCreateDto;
import org.example.food.dtos.gallerydtos.GalleryDto;
import org.example.food.dtos.gallerydtos.GalleryHomeDto;
import org.example.food.dtos.gallerydtos.GalleryUpdateDto;

import java.util.List;

public interface GalleryService {
    void addPhoto(GalleryCreateDto galleryCreateDto);
    List<GalleryDto> getGallery();
    void removePhoto(int id);
    void updatePhoto(GalleryUpdateDto galleryUpdateDto);
    GalleryUpdateDto findUpdatedPhoto(int id);
    List<GalleryHomeDto> getHomeGallery();
}
