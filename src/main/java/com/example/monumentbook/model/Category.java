package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String description;
    private String coverImage;
    private boolean deleted;
    private LocalDate date;
    public CategoryDto toDto(){
        return  new CategoryDto(this.id,this.name, this.description, this.coverImage);
    }

}
