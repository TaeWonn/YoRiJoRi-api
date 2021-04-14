package dev.ohjj.yorijori.api.image.menu;

import dev.ohjj.yorijori.api.image.ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuImageService {

    private final ImageUploader imageUploader;


}
