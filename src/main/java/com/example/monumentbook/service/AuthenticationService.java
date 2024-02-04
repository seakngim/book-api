package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.SignUpRequest;
import com.example.monumentbook.model.requests.SigninRequest;
import com.example.monumentbook.model.responses.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    ResponseEntity<?> signin(SigninRequest request);

}
