package com.example.monumentbook.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

}
