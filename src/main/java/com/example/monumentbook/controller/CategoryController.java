package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.CategoryRequest;
import com.example.monumentbook.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("Api/v1/category")
public class CategoryController {
        private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/All")
    public ResponseEntity<?> getAllCategory(){
        return categoryService.findAllCategory();
    }
    @PostMapping("/add")
    public  ResponseEntity<?> AddCategory(CategoryRequest categoryRequest){
        return categoryService.saveCategory(categoryRequest);
    }

}
