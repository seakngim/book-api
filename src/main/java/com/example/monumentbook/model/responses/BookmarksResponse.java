package com.example.monumentbook.model.responses;

import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.dto.UserDto;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookmarksResponse {
    private Integer id;
    private UserDto user;
    private BookDto book;
    private LocalDate date;
}
