package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.NewsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "news_tb")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(length = 5000)
    private String description;
    private String coverImg;
    private LocalDate date;
    @Column(name = "`delete`")
    private Boolean delete;

    public NewsDto toDto() {
        return new NewsDto(this.id,this.title,this.description,this.coverImg,this.getDate(), this.delete);
    }
}
