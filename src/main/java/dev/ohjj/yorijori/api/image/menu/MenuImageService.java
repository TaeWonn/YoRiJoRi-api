package dev.ohjj.yorijori.api.image.menu;

import dev.ohjj.yorijori.api.controller.request.ImageRequest;
import dev.ohjj.yorijori.api.controller.response.ImageResponse;
import dev.ohjj.yorijori.api.image.ImageUploader;
import dev.ohjj.yorijori.api.persistence.image.entity.MenuImageEntity;
import dev.ohjj.yorijori.api.persistence.image.repository.MenuImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MenuImageService {

    private final ImageUploader imageUploader;
    private final MenuImageRepository menuImageRepository;

    private static final String PATH = "menu";

    public ImageResponse.Upload upload(MultipartFile multipartFile) {
        ImageRequest.Common image = imageUploader.upload(multipartFile, PATH);

        MenuImageEntity imageEntity = MenuImageEntity.from(
                image.getPath()
                , image.getFileName()
        );

        menuImageRepository.save(imageEntity);

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

    public ImageResponse.SelectOne findById(Long menuImageSeq) {
        MenuImageEntity imageEntity = menuImageRepository.findById(menuImageSeq)
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

    public void deleteById(Long menuImageSeq) {
        MenuImageEntity menuImageEntity = menuImageRepository.findById(menuImageSeq)
                .orElseThrow(() -> new NoSuchElementException(""));

        menuImageEntity.setDeleteAt(true);
        menuImageEntity.setModDate(LocalDateTime.now());

        imageUploader.remove(menuImageEntity.getPath(), menuImageEntity.getFileName());
    }
}
