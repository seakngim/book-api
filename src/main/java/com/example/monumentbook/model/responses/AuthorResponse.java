package com.example.monumentbook.model.responses;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class AuthorResponse {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookDto> book;
}
