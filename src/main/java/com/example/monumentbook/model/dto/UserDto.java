package com.example.monumentbook.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String username;
    private String phoneNumber;
    private String email;
    private String coverImage;
}
