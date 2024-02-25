package com.example.monumentbook.model.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse <T>{
    private boolean status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<?> data;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;}

