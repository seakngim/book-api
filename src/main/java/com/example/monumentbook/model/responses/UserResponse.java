package com.example.monumentbook.model.responses;

import com.example.monumentbook.enums.Role;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
