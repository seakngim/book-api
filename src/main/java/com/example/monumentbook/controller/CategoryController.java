package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.CategoryRequest;
import com.example.monumentbook.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue =  "10")Integer size){
        return categoryService.findAllCategory(page, size);
    }
    @PostMapping("/add")
    public  ResponseEntity<?> AddCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.saveCategory(categoryRequest);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(@Param("Category id Integer") Integer id){
        return categoryService.findById(id);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@Param("Category id Integer") Integer id , @RequestBody CategoryRequest request){
        return categoryService.UpdateById(id,request);
    }@DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@Param("Category id Integer") Integer id){
        return categoryService.deleteById(id);
    }


}
