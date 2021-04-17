package dev.ohjj.yorijori.api.image.restaurant;

import dev.ohjj.yorijori.api.controller.request.ImageRequest;
import dev.ohjj.yorijori.api.controller.response.ImageResponse;
import dev.ohjj.yorijori.api.image.ImageUploader;
import dev.ohjj.yorijori.api.persistence.image.entity.RestaurantImageEntity;
import dev.ohjj.yorijori.api.persistence.image.repository.RestaurantImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantImageService {

    private final ImageUploader imageUploader;
    private final RestaurantImageRepository restaurantImageRepository;
    private static final String PATH = "restaurant";

    public ImageResponse.Upload upload(MultipartFile multipartFile) {
        ImageRequest.Common image = imageUploader.upload(multipartFile, PATH);

        RestaurantImageEntity imageEntity = RestaurantImageEntity.from(
                image.getPath()
                , image.getFileName()
        );

        restaurantImageRepository.save(imageEntity);

        return ImageResponse.Upload.from(
                imageEntity.getSeq()
                , imageEntity.getPath()
                , imageEntity.getFileName()
                , image.getUrl()
                , imageEntity.getRegDate()
                , imageEntity.getModDate()
                , imageEntity.getDeleteAt()
        );
    }

    public ImageResponse.SelectOne findById(Long imageSeq) {
        RestaurantImageEntity imageEntity = restaurantImageRepository.findById(imageSeq)
                .orElseThrow(() -> new NoSuchElementException(""));

        return ImageResponse.SelectOne.from(
                imageEntity.getSeq()
                , imageEntity.getPath()
                , imageEntity.getFileName()
                , imageEntity.getRegDate()
                , imageEntity.getModDate()
                , imageEntity.getDeleteAt()
        );
    }

    public void deleteById(Long imageSeq) {
        RestaurantImageEntity imageEntity = restaurantImageRepository.findById(imageSeq)
                .orElseThrow(() -> new NoSuchElementException(""));

        imageEntity.setDeleteAt(true);
        imageEntity.setModDate(LocalDateTime.now());

        imageUploader.remove(imageEntity.getPath(), imageEntity.getFileName());
    }
}
