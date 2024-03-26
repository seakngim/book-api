package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.BookDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String title;
    @Column(length = 3000)
    private String description;
    @Column(unique = true)
    private String isbn;
    private String publisher;
    private String coverImg;
    private LocalDate publishDate;
    private int qty;
    private int price;
    @Column(name = "is_delete")
    private boolean delete;
    @Column(name = "of_the_week")
    private boolean ofTheWeek;
    @Column(name = "best_sell")
    private boolean bestSell;
    @Column(name = "new_arrival")
    private boolean newArrival;
    public BookDto toDto() {
        return new BookDto(
                this.id,
                this.title,
                this.description,
                this.coverImg,
                this.isbn,
                this.publisher,
                this.publishDate,
                this.getQty(),
                this.getPrice()
        );
    }

}
