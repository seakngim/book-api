package com.example.monumentbook.service;

import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> allCustomerOrder();
    ResponseEntity<?> allCurrentOrder();
}
