package dev.ohjj.yorijori.api;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YorijoriApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YorijoriApiApplication.class, args);
    }
}
