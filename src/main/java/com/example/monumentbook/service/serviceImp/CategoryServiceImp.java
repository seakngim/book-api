package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.*;
import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.requests.CategoryRequest;
import com.example.monumentbook.model.responses.ApiResponse;
import com.example.monumentbook.model.responses.CategoryResponse;
import com.example.monumentbook.repository.BookCategoryRepository;
import com.example.monumentbook.repository.BookRepository;
import com.example.monumentbook.repository.CategoryRepository;
import com.example.monumentbook.service.CategoryService;
import com.example.monumentbook.utilities.response.ResponseObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;


    public CategoryServiceImp(CategoryRepository categoryRepository, BookRepository bookRepository, BookCategoryRepository bookCategoryRepository, BookRepository bookRepository1) {
        this.categoryRepository = categoryRepository;
        this.bookCategoryRepository = bookCategoryRepository;
        this.bookRepository = bookRepository1;
    }

    @Override
    public ResponseEntity<?> findAllCategory(Integer page,Integer size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<Category> pageResult = categoryRepository.findByDeletedFalse(pageable);
            List<CategoryResponse> categoryResponseList = new ArrayList<>();
            for(Category bookCategory : pageResult.getContent()){
                List<BookDto> books = bookFlags(bookCategory);
                CategoryResponse categoryResponse = CategoryResponse.builder()
                        .id(bookCategory.getId())
                        .name(bookCategory.getName())
                        .description(bookCategory.getDescription())
                        .coverImage(bookCategory.getCoverImage())
                        .books(books)
                        .date(bookCategory.getDate())
                        .build();
                categoryResponseList.add(categoryResponse);
            }
            ApiResponse res = new ApiResponse(true, "Update books successful!", categoryResponseList, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
            return ResponseEntity.ok(res);
        }
        catch (Exception e){
            ApiResponse res = new ApiResponse(true, "update books False!",null, 0, 0, 0, 0);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(res);
        }
    }
    @Override
    public ResponseEntity<?> saveCategory(CategoryRequest category) {

        try {
            Category categoryObj = null;
            categoryObj =  Category.builder()
                    .name(category.getName())
                    .description(category.getDescription())
                    .coverImage(category.getCoverImage())
                    .date(LocalDate.now())
                    .build();
            categoryRepository.save(categoryObj);
            return ResponseEntity.ok(ApiResponse.<CategoryResponse>builder()
                    .message("susses")
                    .status(true)
                    .build());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("add category unsuccessful");
        }
    }

    @Override
    public ResponseEntity<?> findById(Integer id) {

        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            ResponseObject res = new ResponseObject();
              if (categoryOptional.isPresent()){
                  List<BookDto> books = bookFlags(categoryOptional.get());
                  CategoryResponse categoryResponse = CategoryResponse.builder()
                          .id(categoryOptional.get().getId())
                          .name(categoryOptional.get().getName())
                          .description(categoryOptional.get().getDescription())
                          .coverImage(categoryOptional.get().getCoverImage())
                          .date(categoryOptional.get().getDate())
                          .books(books)
                          .build();
                  res.setMessage("fetch data successful!");
                  res.setStatus(true);
                  res.setData(categoryResponse);
              }
            return ResponseEntity.ok(res);
        }
        catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("fetch data successful!");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(res);
        }
    }

    @Override
    public ResponseEntity<?> UpdateById(Integer id, CategoryRequest categoryRequest) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            ResponseObject res = new ResponseObject();
            if (categoryOptional.isPresent()){
                Category category = Category.builder()
                        .id(categoryOptional.get().getId())
                        .name(categoryRequest.getName())
                        .description(categoryRequest.getDescription())
                        .coverImage(categoryRequest.getCoverImage())
                        .date(categoryOptional.get().getDate())
                        .build();
                categoryRepository.save(category);
                CategoryResponse categoryResponse = CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .coverImage(category.getCoverImage())
                        .date(category.getDate())
                        .build();
                res.setMessage("fetch data successful!");
                res.setStatus(true);
                res.setData(categoryResponse);
            }
            return ResponseEntity.ok(res);
        }
        catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("fetch data successful!");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(res);
        }
    }
    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            ResponseObject res = new ResponseObject();
            if (categoryOptional.isPresent()){
                Category category = Category.builder()
                        .id(categoryOptional.get().getId())
                        .name(categoryOptional.get().getName())
                        .description(categoryOptional.get().getDescription())
                        .coverImage(categoryOptional.get().getCoverImage())
                        .deleted(true)
                        .date(categoryOptional.get().getDate())
                        .build();
                categoryRepository.save(category);
                CategoryResponse categoryResponse = CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .coverImage(category.getCoverImage())
                        .date(category.getDate())
                        .build();
                res.setMessage("delete data successful!");
                res.setStatus(true);
                res.setData(categoryResponse);
            }
            return ResponseEntity.ok(res);
        }
        catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("fetch data successful!");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(res);
        }
    }
    private  List<BookDto> bookFlags(Category category){
        List<BookCategory> bookCategoryList = bookCategoryRepository.findAllByCategory(category);
        List<BookDto> authorBooks = new ArrayList<>();
        for(BookCategory bookCategory : bookCategoryList){
            Optional<Book> bookOptional = bookRepository.findById(bookCategory.getBook().getId());
            if(bookOptional.isPresent()){
                BookDto book = BookDto.builder()
                        .id(bookOptional.get().getId())
                        .title(bookOptional.get().getTitle())
                        .isbn(bookOptional.get().getIsbn())
                        .coverImg(bookOptional.get().getCoverImg())
                        .description(bookOptional.get().getDescription()).price(bookOptional.get().getPrice())
                        .qty(bookOptional.get().getQty())
                        .publishDate(bookOptional.get().getPublishDate())
                        .publisher(bookOptional.get().getPublisher())
                        .build();
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }
}
