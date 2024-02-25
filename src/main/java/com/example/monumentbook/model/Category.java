package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;
    private String name;
    private String description;
    @Column(name = "delete")
    private boolean delete;

    public CategoryDto toDto(){
        return  new CategoryDto(this.id,this.name, this.description);
    }

}
