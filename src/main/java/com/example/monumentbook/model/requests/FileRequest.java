package com.example.monumentbook.model.requests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRequest {

    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long size;

}
