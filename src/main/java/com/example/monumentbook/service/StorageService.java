package com.example.monumentbook.service;

import com.example.monumentbook.model.ImageData;
import com.example.monumentbook.repository.StorageRepository;
import com.example.monumentbook.utiles.ImageUtils;
import com.example.monumentbook.utilities.response.ResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {
    ResponseObject res = new ResponseObject();
    @Autowired
    private StorageRepository repository;

    public ResponseEntity<?> uploadImage(MultipartFile file, HttpServletRequest request) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(UUID.randomUUID().toString())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            // Assuming that you have a method to get the base URL
            StringBuffer baseUrl = request.getRequestURL();
            // Generate the URL using the ID of the saved entity

            String imageUrl = baseUrl + imageData.getName();
            res.setMessage("File uploaded successfully");
            res.setStatus( true);
            res.setData(imageUrl);
            return ResponseEntity.ok(res);
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images= ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
