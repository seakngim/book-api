package com.example.monumentbook.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDetailsService userDetailsService();
//    ResponseEntity<?> findAllUser();
//    ResponseEntity<?> findUserBySubject(long id);
//    ResponseEntity<?> findUserByShift(long id);
//    ResponseEntity<?> findUser();
}
