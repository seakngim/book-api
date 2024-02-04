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
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase())).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .token(jwt).build();
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
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .role(user.getRole().name())
                    .token(jwt).build());
        } catch (AuthenticationException e) {
            // Handle authentication failure
            emptyObject.setStatus(false);
            emptyObject.setMessage(message.loginFail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyObject);
        }
    }
}
