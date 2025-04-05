package org.example.miniprojectspring.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.FileMetadata;
import org.example.miniprojectspring.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<FileMetadata>> uploadFile(@RequestParam MultipartFile file){
        ApiResponse<FileMetadata> apiResponse = ApiResponse.<FileMetadata>builder()
                .success(true)
                .message("Upload file successfully")
                .httpStatus(HttpStatus.CREATED)
                .payload(fileService.uploadFile(file))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @SneakyThrows
    @GetMapping("/preview-file/{file-name}")
    public ResponseEntity<?> getFileByFileName(@PathVariable("file-name") String fileName){
        InputStream inputStream= fileService.getFileByFileName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(inputStream.readAllBytes());
    }

}

