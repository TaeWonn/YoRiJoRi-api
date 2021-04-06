package dev.ohjj.yorijori.api.image.util;

import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileNameUtils {

    public static String randomFileName () {
        String uuid = UUID.randomUUID().toString();
        LocalDateTime date = LocalDateTime.now();
        String now = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        return uuid + "-" + now;
    }

    public static String getExtension(MultipartFile file) {
        return Files.getFileExtension(file.getOriginalFilename());
    }
}
