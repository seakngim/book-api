package com.example.monumentbook.controller;

import com.example.monumentbook.service.SearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/search")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    @GetMapping("/all")
    public ResponseEntity<?> getCurrentUser(@Param("filter") String filter){
        return searchService.search(filter);
    }
}
