package com.example.monumentbook.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
}
