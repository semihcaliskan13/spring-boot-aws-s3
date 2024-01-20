package com.example.aws.controller;

import com.example.aws.payload.FileDto;
import com.example.aws.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileUploadService;

    public FileController(FileService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping(value = "/bucket/create/{bucketName}")
    public String createBucket(@PathVariable String bucketName) {
        return fileUploadService.createBucket(bucketName);
    }

    @GetMapping(value = "/bucket/list")
    public List<String> getBucketList() {
        return fileUploadService.getBucketList();
    }

    @GetMapping(value = "/bucket/files/{bucketName}")
    public List<FileDto> getBucketfiles(@PathVariable String bucketName) {
        return fileUploadService.getBucketfiles(bucketName);
    }

    @DeleteMapping(value = "/bucket/delete/hard/{bucketName}")
    public String hardDeleteBucket(@PathVariable String bucketName) {
        return fileUploadService.hardDeleteBucket(bucketName);
    }

    @DeleteMapping(value = "/bucket/delete/{bucketName}")
    public String softDeleteBucket(@PathVariable String bucketName) {
        return fileUploadService.softDeleteBucket(bucketName);
    }

    @PostMapping(value = "/file/upload/{bucketName}")
    public String fileUplaod(@PathVariable String bucketName, MultipartFile file) {
        return fileUploadService.fileUplaod(bucketName, file);
    }

    @DeleteMapping(value = "/file/delete/{bucketName}/{fileName}")
    public String deleteFile(@PathVariable String bucketName, @PathVariable String fileName) {
        return fileUploadService.deleteFile(bucketName, fileName);
    }

    @GetMapping(value = "/file/download/{bucketName}/{fileName}")
    public StreamingResponseBody downloadFile(@PathVariable String bucketName, @PathVariable String fileName,
                                              HttpServletResponse httpResponse) {
        FileDto downloadFile = fileUploadService.downloadFile(bucketName, fileName);
        httpResponse.setContentType("application/octet-stream");
        httpResponse.setHeader("Content-Disposition",
                String.format("inline; filename=\"%s\"", downloadFile.getFileName()));
        return outputStream -> {
            outputStream.write(downloadFile.getFile());
            outputStream.flush();
        };
    }
}
