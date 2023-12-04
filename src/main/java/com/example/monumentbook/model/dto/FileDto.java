package com.example.monumentbook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long size;

}
