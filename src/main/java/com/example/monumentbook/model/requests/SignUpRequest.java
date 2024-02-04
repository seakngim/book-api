package com.example.monumentbook.model.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String Role = "USER";
}
