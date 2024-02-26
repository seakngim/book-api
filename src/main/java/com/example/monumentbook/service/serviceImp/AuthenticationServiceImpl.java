package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.enums.Role;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.requests.SignUpRequest;
import com.example.monumentbook.model.requests.SigninRequest;
import com.example.monumentbook.model.responses.JwtAuthenticationResponse;
import com.example.monumentbook.repository.UserRepository;
import com.example.monumentbook.service.AuthenticationService;
import com.example.monumentbook.service.JwtService;
import com.example.monumentbook.utilities.response.EmptyObject;
import com.example.monumentbook.utilities.response.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final
    EmptyObject emptyObject = new EmptyObject();
    Message message = new Message();
    @Override
    public ResponseEntity<?> signup(SignUpRequest request) {
        try {
        var user = User.builder().username(request.getUsername()).phoneNumber(request.getPhoneNumber())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase())).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole().name())
                    .token(jwt).build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle generic signup failure
            emptyObject.setStatus(false);
            emptyObject.setMessage("Create account fail");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyObject);
        }
    }
    @Override
    public  ResponseEntity<?> signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

            var jwt = jwtService.generateToken(user);


            return ResponseEntity.ok(JwtAuthenticationResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .phoneNumber(user.getUsername())
                    .role(user.getRole().name())
                    .token(jwt).build());
        } catch (AuthenticationException e) {
            // Handle authentication failure
            emptyObject.setStatus(false);
            emptyObject.setMessage(message.loginFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyObject);
        }
    }
}
