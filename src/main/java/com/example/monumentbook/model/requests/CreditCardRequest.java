package com.example.monumentbook.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardRequest {
    private String fullName;
    private long cardNumber;
    private int cvv;
    private int expiryMonth;
    private int expiryYear;
    private String address;
    private String city;
}
