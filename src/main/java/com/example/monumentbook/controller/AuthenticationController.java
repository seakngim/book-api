package com.example.monumentbook.controller;


import com.example.monumentbook.model.requests.SignUpRequest;
import com.example.monumentbook.model.requests.SigninRequest;
import com.example.monumentbook.model.responses.JwtAuthenticationResponse;
import com.example.monumentbook.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}
