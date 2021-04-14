package dev.ohjj.yorijori.api.controller;

import dev.ohjj.yorijori.api.image.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image/common")
public class ImageController {

    private final ImageUploader imageCommonService;
    private static final String DIRECTORY_NAME = "common";



}
