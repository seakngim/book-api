package com.example.monumentbook.service;

import org.springframework.http.ResponseEntity;

public interface SearchService {
    ResponseEntity<?> search(String filter);
}
