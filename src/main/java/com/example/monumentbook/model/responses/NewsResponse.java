package com.example.monumentbook.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsResponse {  private Integer id;
    private String title;
    private String description;
    private String coverImg;
    private LocalDate date;

}
