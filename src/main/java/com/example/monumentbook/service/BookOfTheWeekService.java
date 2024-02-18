package com.example.monumentbook.service;

import org.springframework.http.ResponseEntity;

public interface BookOfTheWeekService {
    ResponseEntity<?> getAll();
}
