package com.example.monumentbook.model.responses;

import com.example.monumentbook.enums.Role;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String username;
    private String phoneNum;
    private String email;
    private String coverImg;
    private String address;
    private Role role;
}
