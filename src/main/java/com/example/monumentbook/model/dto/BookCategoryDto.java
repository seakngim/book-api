package com.example.monumentbook.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCategoryDto {
    private Integer id;
    private String categoryName;
    private String Description;
}
