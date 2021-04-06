package dev.ohjj.yorijori.api.image.controller;

import dev.ohjj.yorijori.api.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;
    private static final String TEMP = "temp";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        String imagePath = null;
        try {
            imagePath = imageService.upload(multipartFile, TEMP);

        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
        
        return imagePath;
    }
}
