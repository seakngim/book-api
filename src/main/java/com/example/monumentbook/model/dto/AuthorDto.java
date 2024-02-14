package com.example.monumentbook.model.dto;

import com.example.monumentbook.model.Book;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDto {
    private Integer id;
    private String name;
    private String description;
    private String image;

}
