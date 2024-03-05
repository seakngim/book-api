package com.example.monumentbook.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Integer id;
    private String customer_name;
    private String phoneNumber;
    private long customer_id;
    private String product_name;
    private Integer product_id;
    private int qty;
    private int price;
    private LocalDate date;
}
