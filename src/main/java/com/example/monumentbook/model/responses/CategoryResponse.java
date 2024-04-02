package com.example.monumentbook.model.responses;

import com.example.monumentbook.model.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
    private Integer id;
    private String name;
    private String description;
    private String coverImage;
    private LocalDate date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookDto> books;


}
