package com.example.monumentbook.model.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
    private Integer id;
    private String name;
    private String description;

}
