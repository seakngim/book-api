package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.AuthorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    @Column(name = "deleted")
    private boolean deleted = false;
    private boolean status;
    private String quote;

public AuthorDto toDto(){
    return new AuthorDto(this.id, this.name, this.description, this.image);
}

}
