package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.PositionsDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "positions")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Positions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;
    private String description;

    public PositionsDTO toDto(){
        return new PositionsDTO(this.id, this.name, this.description);
    }
}
