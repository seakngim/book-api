package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.NewsRequest;
import com.example.monumentbook.service.NewsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/news")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    @PostMapping("/add")
    ResponseEntity<?> addNews(@RequestBody NewsRequest newsRequest){
        return newsService.addNews(newsRequest);
    }
   @GetMapping("/getAll")
    ResponseEntity<?> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        return newsService.getNews(page, size);
    }
    @PutMapping("/update")
    ResponseEntity<?> updateById(@Param("news id") Integer id, @RequestBody NewsRequest newsRequest){
        return newsService.updateNews(id,newsRequest);
    }
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteById(@Param("news id") Integer id){
        return newsService.deleteNews(id);
    }
    @GetMapping ("/getById")
    ResponseEntity<?> getById(@Param("news id") Integer id){
        return newsService.getById(id);
    }

}
