package com.example.monumentbook.controller;

import com.example.monumentbook.service.SearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/search")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class SearcherController {
    private final SearchService searchService;
    @GetMapping("searchBy")
    public ResponseEntity<?> getCurrentUser(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return searchService.search(page, size);
    }
}
