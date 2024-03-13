package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.AuthorRequest;
import com.example.monumentbook.service.AuthorService;
import com.example.monumentbook.service.serviceImp.AuthorServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(authorService.add(authorRequest));
    }
    @GetMapping("/all")
    public ResponseEntity<?> all(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity.ok(authorService.getAll(page, size));
    }
    @GetMapping("/byId")
    public ResponseEntity<?> byId(@Param("author Id") Integer id){
        return ResponseEntity.ok(authorService.getById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@Param("author Id") Integer id,@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(authorService.update(id, authorRequest));
    }
    @DeleteMapping("/deleted")
    public ResponseEntity<?> deleted(@Param("author Id") Integer id){
        return ResponseEntity.ok(authorService.deleted(id));
    }
    @GetMapping("/feature")
    public ResponseEntity<?> feature(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity.ok(authorService.getFeature(page, size));
    }
    @PostMapping("/addFeature")
    public ResponseEntity<?> addFeature(@Param("id") Integer id){
        return ResponseEntity.ok(authorService.addFeature(id));
    }
}
