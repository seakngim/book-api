package com.example.monumentbook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private Integer id;
    private String title;
    private String description;
    private String coverImg;
    private LocalDate date;
    private boolean delete;
}
