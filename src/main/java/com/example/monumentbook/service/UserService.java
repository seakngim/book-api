package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDetailsService userDetailsService();
    ResponseEntity<?> findAllUser();
    ResponseEntity<?> findCurrentUser();
    ResponseEntity<?> findUserById(Integer id);
    ResponseEntity<?> updateCurrentUser(UserRequest userRequest);
}
