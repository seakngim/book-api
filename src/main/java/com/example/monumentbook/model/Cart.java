package com.example.monumentbook.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart_tb")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "userId")
    private User userId;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "bookId")
    private Book bookId;
    private int qty;
    private LocalDate date;
    private boolean deleted;
}
