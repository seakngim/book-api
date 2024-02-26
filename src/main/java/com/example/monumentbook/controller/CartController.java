package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.CartRequest;
import com.example.monumentbook.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
@SecurityRequirement(name = "bearerAuth")
public class CartController {
    private final CartService cartService;
    @GetMapping("/all")
    @Operation(summary = "get all cart")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        return cartService.getCart(page, size);
    }
    @PostMapping("/add")
    @Operation(summary = "add new cart")
    public  ResponseEntity<?> addAll(@RequestBody CartRequest cartRequest){
        return cartService.addToCart(cartRequest);
    }
    @GetMapping("/getById")
    @Operation(summary = "get cart by id")
    public ResponseEntity<?> getById(@Param("cart id INTEGER") Integer id){
        return cartService.getCartById(id);
    }
    @DeleteMapping("/delete")
    @Operation(summary = "delete cart by id")
    public ResponseEntity<?> delete(@Param("cart id INTEGER") Integer id){
       return cartService.deleteCartById(id);
    }
    @PutMapping("/update")
    @Operation(summary = "Update cart by id")
    public ResponseEntity<?> update(@Param("CART ID INTEGER")Integer id,@RequestBody CartRequest cartRequest){
        return cartService.updateCartById(id,cartRequest);
    }

}
