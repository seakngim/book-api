package com.example.monumentbook.model.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String Role = "USER";
}
