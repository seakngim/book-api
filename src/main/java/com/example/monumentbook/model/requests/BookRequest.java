package com.example.monumentbook.model.requests;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@Builder
public class BookRequest {
    private String title;
    private String description;
    private String coverImg;
    private String isbn;
    private String publisher;
    private LocalDate publishDate;
    private List<Integer> categoryId;
    private List<Integer> authorId;
}
