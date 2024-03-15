package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.BookmarksRequest;
import com.example.monumentbook.service.BookmarksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bookmarks")
@SecurityRequirement(name = "bearerAuth")
public class BookMarksController {
    private final BookmarksService bookmarksService;
    @PostMapping("/add")
    @Operation(summary = "add new cart")
    public ResponseEntity<?> addAll(@RequestBody BookmarksRequest bookmarksRequest){
        return bookmarksService.addToBookmarks(bookmarksRequest);
    }
    @GetMapping("/byCurrentUser")
    @Operation(summary = "get all bookmark by CurrentUser")
    public ResponseEntity<?> getAllCurrentUser(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        return bookmarksService.findBookmarksByUser(page, size);
    }
    @DeleteMapping("/delete")
    @Operation(summary = "delete cart by id")
    public ResponseEntity<?> delete(@Param("cart id INTEGER") Integer id){
        return bookmarksService.deleteCartById(id);
    }
}
