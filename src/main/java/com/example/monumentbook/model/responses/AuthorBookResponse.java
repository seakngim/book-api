package com.example.monumentbook.model.responses;

import com.example.monumentbook.model.Author;
import com.example.monumentbook.model.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class AuthorBookResponse {

    private Integer id;
    private Author authors;
    private Book book;

}
