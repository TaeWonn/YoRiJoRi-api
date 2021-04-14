package dev.ohjj.yorijori.api.image.restaurant;

import dev.ohjj.yorijori.api.controller.request.ImageRequest;
import dev.ohjj.yorijori.api.image.ImageUploader;
import dev.ohjj.yorijori.api.persistence.image.repository.RestaurantImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RestaurantImageService {

    private final ImageUploader imageUploader;
    private final RestaurantImageRepository restaurantImageRepository;
    private static final String PATH = "restaurant";

    public void uploadImage(MultipartFile multipartFile) {
        ImageRequest.Common image = imageUploader.upload(multipartFile, PATH);


    }
}
