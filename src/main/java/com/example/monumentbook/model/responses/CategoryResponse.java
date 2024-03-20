package com.example.monumentbook.model.responses;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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

}
