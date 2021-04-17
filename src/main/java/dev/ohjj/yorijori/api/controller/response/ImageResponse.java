package dev.ohjj.yorijori.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ImageResponse {

    @Getter
    @AllArgsConstructor
    public static class Upload {
        private final Long seq;
        private final String path;
        private final String fileName;
        private final String url;
        private final LocalDateTime regDate;
        private final LocalDateTime modDate;
        private final Boolean deleteAt;

        public static Upload from (Long seq, String path, String fileName, String url
                        , LocalDateTime regDate, LocalDateTime modDate, Boolean deleteAt) {
            return new Upload(seq, path, fileName, url, regDate, modDate, deleteAt);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class SelectOne {
        private final Long seq;
        private final String path;
        private final String fileName;
        private final LocalDateTime regDate;
        private final LocalDateTime modDate;
        private final Boolean deleteAt;

        public static SelectOne from (Long seq, String path, String fileName
                                , LocalDateTime regDate, LocalDateTime modDate, Boolean deleteAt) {
            return new SelectOne(seq, path, fileName, regDate, modDate, deleteAt);
        }
    }

}
