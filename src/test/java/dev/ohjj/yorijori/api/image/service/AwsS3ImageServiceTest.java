package dev.ohjj.yorijori.api.image.service;

import dev.ohjj.yorijori.api.image.util.FileNameUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AwsS3ImageServiceTest {

    @Test
    void ImageUUIDTest() {
        //given
        String path;

        //when
        path = FileNameUtils.randomFileName();
        System.out.println(path);

        //then
        assertNotNull(path);
    }


}