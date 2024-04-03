package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.CreditCardRequest;
import org.springframework.http.ResponseEntity;

public interface CreditCardService {
    ResponseEntity<?> addCard(CreditCardRequest creditCardRequest);
}
