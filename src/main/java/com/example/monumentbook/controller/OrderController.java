package com.example.monumentbook.controller;

import com.example.monumentbook.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/order")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrder(){
        return orderService.allCustomerOrder();
    }
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(){
        return orderService.allCurrentOrder();
    }
}
