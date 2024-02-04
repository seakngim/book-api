package com.example.monumentbook.model.dto;

import com.example.monumentbook.model.Positions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositionsDTO {

    private int id;
    private String name;

    @JsonIgnore
    private String description;

    public Positions toPositions() {
        return new Positions(
                this.id,
                this.name,
                this.description
        );
    }
}
