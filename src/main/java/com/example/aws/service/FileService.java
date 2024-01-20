package com.example.aws.service;

import com.example.aws.payload.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    String fileUplaod(String bucketName, MultipartFile file);

    String createBucket(String bucketName);

    List<String> getBucketList();

    List<FileDto> getBucketfiles(String bucketName);

    String softDeleteBucket(String bucketName);

    String hardDeleteBucket(String bucketName);

    String deleteFile(String bucketName, String fileName);

    FileDto downloadFile(String bucketName, String fileName);
}
