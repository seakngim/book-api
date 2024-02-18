package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.ProductRequest;
import com.example.monumentbook.model.requests.BookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    ResponseEntity<?> findAllBook();
    ResponseEntity<?> findBookById(Integer id);

    ResponseEntity<?> saveBook(BookRequest book);
    ResponseEntity<?> updateBook(BookRequest book, Integer id);
    ResponseEntity<?> DeleteById(Integer id);
    ResponseEntity<?> addProductById(Integer id, ProductRequest productRequest);
    ResponseEntity<?> outProductById(Integer id , ProductRequest productRequest);
    ResponseEntity<?> addBookOfTheWeek(Integer id);
    ResponseEntity<?> getBookOfTheWeek();

}
