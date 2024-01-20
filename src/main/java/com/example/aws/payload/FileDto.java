package com.example.aws.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileDto {
    private String fileName;
    private Long fileSize;
    private String filePath;
    private byte[] file;

    public FileDto(String fileName, Long fileSize, String filePath) {
        super();
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }
}
