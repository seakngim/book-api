package com.example.monumentbook.service;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.requests.BookRequest;
import com.example.monumentbook.model.responses.ApiResponse;
import com.example.monumentbook.model.responses.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    ResponseEntity<?> findAllBook();

    ResponseEntity<?> findBookById(Integer id);

    ResponseEntity<?> saveBook(BookRequest book);
    ResponseEntity<?> updateBook(BookRequest book);
}
