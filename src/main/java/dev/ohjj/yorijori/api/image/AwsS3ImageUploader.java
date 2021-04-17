package dev.ohjj.yorijori.api.image;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import dev.ohjj.yorijori.api.common.utils.FileNameUtils;
import dev.ohjj.yorijori.api.controller.request.ImageRequest;
import dev.ohjj.yorijori.api.image.exception.MultipartFileConvertException;
import dev.ohjj.yorijori.api.persistence.image.repository.ImageRepository;
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
public class AwsS3ImageUploader implements ImageUploader {

    private final AmazonS3 client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public ImageRequest.Common upload(MultipartFile multipartFile, String dirName) {
        Optional<File> convertedFile;

        try {
            convertedFile = convert(multipartFile);
        } catch (IOException e) {
            throw new MultipartFileConvertException("Fail MultipartFile to File Converted");
        }

        File file = convertedFile.orElseThrow(
                () -> new MultipartFileConvertException (
                        String.format("Could Not Converted File to %s", multipartFile.getName())
                )
        );

        return upload(file, dirName);
    }

    public void remove(String path ,String fileName) {
        String key = path + "/" + fileName;

        try {
            client.deleteObject(bucket, key);
        } catch (AmazonServiceException e) {
            log.info("AmazonServiceException :: fail to delete Object {}", key);
        } catch (SdkClientException e) {
            log.info("SdkClientException :: exception for amazonSDK");
        }

    }

    private ImageRequest.Common upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();

        String uploadImageUrl = putS3(uploadFile, fileName);

        log.info("Image Upload Result :: {}", uploadImageUrl);
        removeTempFile(uploadFile);

        return new ImageRequest.Common(dirName, uploadFile.getName(), uploadImageUrl);
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
        String extension = FileNameUtils.getExtension(file);
        String fileName = FileNameUtils.randomFileName() + "." + extension;

        File convertFile = new File(fileName);

        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }

            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}
