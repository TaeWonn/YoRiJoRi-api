package dev.ohjj.yorijori.api.image.common;

import dev.ohjj.yorijori.api.controller.request.ImageRequest;
import dev.ohjj.yorijori.api.image.ImageUploader;
import dev.ohjj.yorijori.api.persistence.image.entity.ImageEntity;
import dev.ohjj.yorijori.api.persistence.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CommonImageService {

    private final ImageUploader imageUploader;
    private final ImageRepository imageRepository;

    private static final String PATH = "common";

    public ImageEntity uploadImage(MultipartFile multipartFile) {
        ImageRequest.Common image = imageUploader.upload(multipartFile, PATH);

        ImageEntity imageEntity = ImageEntity.from(
                image.getPath(),
                image.getFileName());

        return imageRepository.save(imageEntity);
    }

}
