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
    private String description;
    private String isbn;
    private String publisher;
    private String coverImg;
    private LocalDate publishDate;
    private Boolean delete;

    public BookDto toDto() {
        return new BookDto(this.id,
                this.title,
                this.description,
                this.coverImg,
                this.isbn,
                this.publisher,
                this.publishDate,
                this.delete

        );
    }

}
