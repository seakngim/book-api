package com.example.monumentbook.controller;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.requests.BookRequest;
import com.example.monumentbook.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("Api/v1/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getBook(){
        return bookService.findAllBook();
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@RequestParam(required = true) Integer id){
        return bookService.findBookById(id);
    }
    @PostMapping("/add")
    @Operation(summary = "Add book")
    public ResponseEntity<?> addBook(@RequestBody BookRequest book){
        return bookService.saveBook(book);
    }
    @PostMapping("/addNewBook")
    @Operation(summary = "Update book")
    public ResponseEntity<?> updateBookById(@RequestBody BookRequest bookRequest){
        return bookService.updateBook( bookRequest);
    }


}
