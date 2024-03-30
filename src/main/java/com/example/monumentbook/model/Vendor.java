package com.example.monumentbook.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Integer id;
    private float cost;
    private Integer book_id;
    private int qty;
    private String name;
    private LocalDate date;
    private boolean deleted;
}
