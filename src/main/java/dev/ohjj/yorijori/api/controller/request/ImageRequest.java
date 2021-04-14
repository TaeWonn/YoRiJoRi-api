package dev.ohjj.yorijori.api.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageRequest {

    @Getter
    @Setter
    public static class Common {
        private String path;
        private String fileName;
        private String url;
    }
}
