package com.example.monumentbook.model.requests;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class BookRequest {
    private String title;
    private String description;
    private String coverImg;
    private double price;
    private int qty;
    private String isbn;
    private String publisher;
    private Date publisherDate;
    private Integer categoryId;
    private List<Integer> authorsId;

}
