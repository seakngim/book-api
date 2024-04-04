package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.CreditCardRequest;
import org.springframework.http.ResponseEntity;

public interface CreditCardService {
    ResponseEntity<?> addCard(CreditCardRequest creditCardRequest);
    ResponseEntity<?> deleteCard(Integer id);
    ResponseEntity<?> updateCard(Integer id, CreditCardRequest creditCardRequest);
    ResponseEntity<?> findCardById(Integer id);
}
