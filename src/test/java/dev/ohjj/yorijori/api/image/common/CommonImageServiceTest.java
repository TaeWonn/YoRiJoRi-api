package dev.ohjj.yorijori.api.image.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommonImageServiceTest {

    @Autowired
    private CommonImageService imageService;

    @Test
    void test(){}

}