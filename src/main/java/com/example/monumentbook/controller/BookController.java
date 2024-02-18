package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.BookRequest;
import com.example.monumentbook.model.requests.ProductRequest;
import com.example.monumentbook.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/book")
@SecurityRequirement(name = "bearerAuth")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getBook(){
        return bookService.findAllBook();
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBookById(@RequestParam(required = true) Integer id){
        return bookService.findBookById(id);
    }
    @PostMapping("/add")
    @Operation(summary = "Add book")
    public ResponseEntity<?> addBook(@RequestBody BookRequest book){
        return bookService.saveBook(book);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update book")
    public ResponseEntity<?> updateBookById(@Param("book id") Integer id,@RequestBody BookRequest bookRequest){
        return bookService.updateBook(bookRequest,id);
    }
    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "delete book")
    public  ResponseEntity<?> deleteBookById(@Param("book id") Integer id){
        return  bookService.DeleteById(id);
    }

    @PostMapping("/addProductById")
    @Operation(summary = "add product by Id")
    public ResponseEntity<?> addProductByName(@Param("book id") Integer id, @RequestBody ProductRequest productRequest){
      return bookService.addProductById(id,productRequest);
    }
    @PostMapping("/outProduct")
    private ResponseEntity<?> outProduct(@Param("book id") Integer id, @RequestBody ProductRequest productRequest){
        return  bookService.outProductById(id,productRequest);
    }
    @PostMapping("/add_book_of_the_week")
    private ResponseEntity<?> addBookOfTheWeek(Integer id){
        return bookService.addBookOfTheWeek(id);
    }
    @PostMapping("/get_book_of_the_week")
    private ResponseEntity<?> getBookOfTheWeek(){
        return bookService.getBookOfTheWeek();
    }


}
