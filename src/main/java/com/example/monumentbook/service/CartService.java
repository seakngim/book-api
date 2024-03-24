package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.CartRequest;
import com.example.monumentbook.model.requests.CartUpdateRequest;
import com.example.monumentbook.model.requests.RequestById;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addToCart(CartRequest cartRequest);
    ResponseEntity<?> getCart(Integer page, Integer size);
    ResponseEntity<?> getCartById(Integer id);
    ResponseEntity<?> updateCartById(Integer id, CartUpdateRequest cartRequest);
    ResponseEntity<?> deleteCartById(RequestById requestById);
    ResponseEntity<?> findCartByUser(Integer page, Integer size);
}
