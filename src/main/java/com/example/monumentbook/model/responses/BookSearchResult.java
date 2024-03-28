package com.example.monumentbook.model.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookSearchResult {
    private Integer id;
    private String title;
    private int price;
    private String description;
    private String coverImg ;
    private String authorName;
    private String categoryName;
}
