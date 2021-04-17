package dev.ohjj.yorijori.api.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AwsS3ImageUploaderTest {

    @Autowired
    private ImageUploader imageUploader;
    private static final String PATH = "test";
    private String key = "";

    @Test
    @Order(1)
    @DisplayName("AWS Image 업로드")
    void upload() {
        // given
        var file = new MockMultipartFile("file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello".getBytes()
        );

        // when
        var upload = imageUploader.upload(file, PATH);

        // then
        assertNotNull(upload.getUrl());
        key = upload.getFileName();
    }

    @Test
    @Order(2)
    @DisplayName("AWS Image 삭제")
    void remove() {
        // 클래스 단위 테스트를 고려하여 작성되었으며,
        // 명시적으로 삭제가 필요한 경우 else 절에 fileName 값을 변경 하기 바람.

        // given
        String fileName;

        if(StringUtils.isNotBlank(this.key))
            fileName = this.key;
        else
            fileName = "template.txt";

        // when
        imageUploader.remove(PATH, fileName);

        //then
    }

}