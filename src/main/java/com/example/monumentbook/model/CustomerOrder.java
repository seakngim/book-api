package com.example.monumentbook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "customer_name")
    private String customerName;
    private String phoneNumber;
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productId")
    private Integer productId;
    private int qty;
    private int price;
    private LocalDate date;
}
