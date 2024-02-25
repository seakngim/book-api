package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.NewsRequest;
import org.springframework.http.ResponseEntity;

public interface NewsService {
    ResponseEntity<?> addNews(NewsRequest newsRequest);
    ResponseEntity<?> getNews( Integer page, Integer size);
    ResponseEntity<?> updateNews(Integer id,NewsRequest newsRequest);
    ResponseEntity<?> deleteNews(Integer id);
    ResponseEntity<?> getById(Integer id);

}
