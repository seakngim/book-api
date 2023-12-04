package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.BookCategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;
    private String name;
    private String description;

    public BookCategoryDto toDto(){
        return  new BookCategoryDto(this.id,this.name, this.description);
    }

}
