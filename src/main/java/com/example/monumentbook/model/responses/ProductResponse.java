package com.example.monumentbook.model.responses;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.Supplier;
import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.dto.SupplierDto;
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
public class ProductResponse {
    private Integer id;
    private int qty;
    private float cost;
    private Integer invoice;
    private float tax;
    private LocalDate date;
    private BookDto book;
    private SupplierDto supplier;

}
