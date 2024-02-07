package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.AuthorRequest;
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
    private final AuthorServiceImp authorServiceImp;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(authorServiceImp.add(authorRequest));
    }
    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(authorServiceImp.getAll());
    }
    @GetMapping("/byId")
    public ResponseEntity<?> byId(@Param("author Id") Integer id){
        return ResponseEntity.ok(authorServiceImp.getById(id));
    }
}
