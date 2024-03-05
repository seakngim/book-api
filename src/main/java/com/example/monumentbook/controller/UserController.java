package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.UserRequest;
import com.example.monumentbook.repository.UserRepository;
import com.example.monumentbook.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/user")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;
   @GetMapping("/allUser")
    @Operation(summary = "get all user from")
    ResponseEntity<?> getAllUser(){
        return userService.findAllUser();
    }
    @GetMapping("/currentUser")
    @Operation(summary = "get all user from")
    ResponseEntity<?> getCurrentUser(){
        return userService.findCurrentUser();
    }
    @GetMapping("/userById")
    @Operation(summary = "get all user from")
    ResponseEntity<?> getUserById(Integer id){
        return userService.findUserById(id);
    }
    @PutMapping("/updateCurrentUser")
    @Operation(summary = "get all user from")
    ResponseEntity<?> updateCurrentUser(UserRequest userRequest){
        return userService.updateCurrentUser(userRequest);
    }

}
