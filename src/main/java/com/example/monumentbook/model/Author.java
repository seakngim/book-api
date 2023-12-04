package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.AuthorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;
    private String name;
    private String biography;

public AuthorDto toDto(){
    return new AuthorDto(this.id, this.name,this.biography);
}

}
