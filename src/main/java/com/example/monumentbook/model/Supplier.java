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
@Builder
@Entity
@Table(name = "supplier_tb")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;
    private String name;
    @Column(length = 1000)
    private String description;
    private String image;
    private LocalDate date;
    private String address;
    @Column(name = "deleted")
    private boolean deleted = false;
}
