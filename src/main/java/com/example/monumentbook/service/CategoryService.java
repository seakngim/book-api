package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.CategoryRequest;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Server
public interface CategoryService {
    ResponseEntity<?> findAllCategory();
    ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest);
}
