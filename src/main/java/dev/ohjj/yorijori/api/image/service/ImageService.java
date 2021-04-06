package dev.ohjj.yorijori.api.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public String upload(MultipartFile multipartFile, String dirName) throws IOException;
}
