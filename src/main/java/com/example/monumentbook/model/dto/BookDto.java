package com.example.monumentbook.model.dto;

import com.example.monumentbook.model.Author;
import com.example.monumentbook.model.BookCategory;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private String description;
    private String coverImg;
    private double price;
    private int qty;
    private String isbn;
    private String publisher;
    private Date publishDate;
    private BookCategoryDto categories;
}
