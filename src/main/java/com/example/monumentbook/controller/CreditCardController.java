package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.CreditCardRequest;
import com.example.monumentbook.repository.CreditCardRepository;
import com.example.monumentbook.service.CreditCardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/card")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;
    @PostMapping("/add")
    public ResponseEntity<?> addCard(@RequestBody CreditCardRequest creditCardRequest){

        return creditCardService.addCard(creditCardRequest);
    }
}
