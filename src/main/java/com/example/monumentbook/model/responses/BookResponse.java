package com.example.monumentbook.model.responses;

import com.example.monumentbook.model.dto.AuthorDto;
import com.example.monumentbook.model.dto.BookCategoryDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {

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
        private List<AuthorDto> author;

}
