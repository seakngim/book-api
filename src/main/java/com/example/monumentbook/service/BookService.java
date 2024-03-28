package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.CustomerRequest;
import com.example.monumentbook.model.requests.ProductRequest;
import com.example.monumentbook.model.requests.BookRequest;
import com.example.monumentbook.model.requests.RequestById;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    ResponseEntity<?> findAllBook(Integer page, Integer size);
    ResponseEntity<?> findBookById(Integer id);
    ResponseEntity<?> saveBook(BookRequest book);
    ResponseEntity<?> updateBook(BookRequest book, Integer id);
    ResponseEntity<?> DeleteById(Integer id);
    ResponseEntity<?> addProductById(Integer id, ProductRequest productRequest);
    ResponseEntity<?> outProductById(List<CustomerRequest> customerRequest);
    ResponseEntity<?> getBookOfTheWeek(Integer page, Integer size);
    ResponseEntity<?> getBestSell(Integer page, Integer size);
    ResponseEntity<?> getNewArrival(Integer page, Integer size);
    ResponseEntity<?> addBookOfTheWeek(RequestById requestById);
    ResponseEntity<?> addBestSell(RequestById requestById);
    ResponseEntity<?> addNewArrival(RequestById requestById);
    ResponseEntity<?> deleteBookOfTheWeek(RequestById requestById);
    ResponseEntity<?> deleteBestSell(RequestById requestById);
    ResponseEntity<?> deleteNewArrival(RequestById requestById);
    ResponseEntity<?> getAllImportProduct(Integer page, Integer size);
    ResponseEntity<?> deleteImport(Integer id);
    ResponseEntity<?> updateImport(Integer id,Integer book_id,ProductRequest productRequest);
    ResponseEntity<?> getImportProductById(Integer id);
//    ResponseEntity<?> findBookBySearch(String keySearch);
}
