package com.example.monumentbook.model.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String title;
    private String description;
    private String coverImg;
    private String isbn;
    private String publisher;
    private LocalDate publishDate;
    private int qty;
    private int price;
}
