package com.example.monumentbook.model.requests;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private int qty;
    private Integer bookId;
}
