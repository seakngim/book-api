package com.example.monumentbook.model.dto;

import com.example.monumentbook.model.Book;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter

public class AuthorDto {
    private String name;
    private String description;
    private String image;

    public AuthorDto(Integer id, String name, String description,String image) {
    }
}
