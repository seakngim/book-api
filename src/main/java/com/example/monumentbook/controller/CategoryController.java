package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.CategoryRequest;
import com.example.monumentbook.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
        private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory(){
        return categoryService.findAllCategory();
    }
    @PostMapping("/add")
    public  ResponseEntity<?> AddCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.saveCategory(categoryRequest);
    }

}
