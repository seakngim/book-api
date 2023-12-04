package com.example.monumentbook.service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Server
public interface ExcelFileUploadService {
    ResponseEntity<?> UploadFile( MultipartFile file);
}
