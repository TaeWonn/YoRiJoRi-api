package dev.ohjj.yorijori.api.image.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import dev.ohjj.yorijori.api.image.entity.ImageEntity;
import dev.ohjj.yorijori.api.image.repository.ImageRepository;
import dev.ohjj.yorijori.api.image.util.FileNameUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsS3ImageService implements ImageService {

    private final AmazonS3 client;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String upload(MultipartFile multipartFile, String dirName) throws IOException {
        Optional<File> convertedFile = convert(multipartFile);

        File file = convertedFile.orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Could Not Converted File to %s", multipartFile.getName()
                        )
                )
        );

        return upload(file, dirName);
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeTempFile(uploadFile);

        ImageEntity imageEntity = ImageEntity.builder()
                .path(dirName)
                .fileName(uploadFile.getName())
                .fileExtension(FileNameUtils.getExtension(uploadFile))

                .build();

        return null;
    }

    private void removeTempFile(File uploadFile) {
        if (!uploadFile.delete())
            log.info("Could Not removed File :: fileName : {}", uploadFile.getName());
    }

    private String putS3(File file, String fileName) {
        client.putObject(new PutObjectRequest(bucket, fileName, file)
            .withCannedAcl(CannedAccessControlList.PublicRead));

        return client.getUrl(bucket, fileName).toString();
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(FileNameUtils.randomFileName());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
