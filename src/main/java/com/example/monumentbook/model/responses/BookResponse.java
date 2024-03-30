package com.example.monumentbook.model.responses;

import com.example.monumentbook.model.dto.AuthorDto;
import com.example.monumentbook.model.dto.CategoryDto;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
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
        private String isbn;
        private String publisher;
        private int qty;
        private float price;
        private boolean delete;
        private boolean ofTheWeek;
        private boolean bestSell;
        private boolean newArrival;
        private LocalDate publishDate;
        private List<CategoryDto> categories;
        private List<AuthorDto> author;

}
