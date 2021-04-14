package dev.ohjj.yorijori.api.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ImageResponse {

    @Getter
    @NoArgsConstructor
    public static class Upload {
        private Long seq;
        private String path;
        private String fileName;
        private String url;

    }

}
