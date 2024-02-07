package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.AuthorRequest;
import org.springframework.http.ResponseEntity;

public interface AuthorService {
    ResponseEntity<?> add(AuthorRequest authorRequest);
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> getAll();
}
