package dev.ohjj.yorijori.api.common.utils;

import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileNameUtils {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd-HH-mm-ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    public static String randomFileName () {
        String uuid = UUID.randomUUID().toString();
        LocalDateTime date = LocalDateTime.now();
        String now = date.format(formatter);
        return uuid + "-" + now;
    }

    public static String getExtension(MultipartFile file) {
        return Files.getFileExtension(file.getOriginalFilename());
    }
}
