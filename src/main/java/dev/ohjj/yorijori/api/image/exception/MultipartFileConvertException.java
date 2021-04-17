package dev.ohjj.yorijori.api.image.exception;

import lombok.NoArgsConstructor;

public class MultipartFileConvertException extends RuntimeException {

    public MultipartFileConvertException() {}

    public MultipartFileConvertException(String message) {
        super(message);
    }
}
