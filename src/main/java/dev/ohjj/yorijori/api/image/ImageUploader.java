package dev.ohjj.yorijori.api.image;

import dev.ohjj.yorijori.api.controller.request.ImageRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    ImageRequest.Common upload(MultipartFile multipartFile, String dirName);

    void remove(String path, String fileName);

}
