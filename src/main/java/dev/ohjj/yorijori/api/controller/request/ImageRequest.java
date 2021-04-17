package dev.ohjj.yorijori.api.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageRequest {

    @Getter
    @AllArgsConstructor
    public static class Common {
        private String path;
        private String fileName;
        private String url;
    }

}
