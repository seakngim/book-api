package com.example.monumentbook.service;

import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> allCustomerOrder(Integer page, Integer size);
    ResponseEntity<?> allCurrentOrder(Integer page, Integer size);
}
