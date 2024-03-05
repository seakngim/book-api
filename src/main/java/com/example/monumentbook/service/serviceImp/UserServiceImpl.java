package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.User;
import com.example.monumentbook.model.requests.UserRequest;
import com.example.monumentbook.model.responses.UserResponse;
import com.example.monumentbook.repository.UserRepository;
import com.example.monumentbook.service.UserService;
import com.example.monumentbook.utilities.response.EmptyObject;
import com.example.monumentbook.utilities.response.Message;
import com.example.monumentbook.utilities.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    ResponseObject res = new ResponseObject();
    Message message = new Message();
    EmptyObject emptyObject = new EmptyObject();

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public ResponseEntity<?> findAllUser() {
        try {
            List<User> users = userRepository.findAll();
            List<UserResponse> userList = new ArrayList<>();
            for (User user : users) {
                Optional<User> userOptional = userRepository.findById((int) user.getId());
                if (userOptional.isPresent()) {
                    userList.add(userResponse(userOptional));
                }
            }
            res.setData(userList);
            res.setMessage("fetch data success!");
            res.setStatus(true);
            return ResponseEntity.ok(res);
        } catch (Exception exception) {
            res.setData(null);
            res.setMessage("fetch data false!");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> findCurrentUser() {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();

            Optional<User> userOptional = userRepository.findById((int) currentUser.getId());
            if (userOptional.isPresent()) {
                res.setData(userResponse(userOptional));
                res.setMessage("fetch data success!");
                res.setStatus(true);
            }
            return ResponseEntity.ok(res);
        } catch (Exception exception) {
            res.setData(null);
            res.setMessage("fetch data false!");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> findUserById(Integer id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                res.setData(userResponse(userOptional));
                res.setMessage("fetch data success!");
                res.setStatus(true);
            }
            return ResponseEntity.ok(res);
        } catch (Exception exception) {
            res.setData(null);
            res.setMessage("fetch data false!");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> updateCurrentUser(UserRequest userRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            Optional<User> userOptional = userRepository.findById((int) currentUser.getId());
            if (userOptional.isPresent()) {
                User user = User.builder()
                        .id(userOptional.get().getId())
                        .email(userOptional.get().getEmail())
                        .phoneNumber(userOptional.get().getPhoneNumber())
                        .username(userOptional.get().getUsername())
                        .role(userOptional.get().getRole())
                        .coverImg(userOptional.get().getCoverImg())
                        .address(userOptional.get().getAddress())
                        .build();
                userRepository.save(user);
                    UserResponse userResponse = UserResponse.builder()
                        .id(userOptional.get().getId())
                        .email(userOptional.get().getEmail())
                        .phoneNum(userOptional.get().getPhoneNumber())
                        .username(userOptional.get().getUsername())
                        .role(userOptional.get().getRole())
                        .coverImg(userOptional.get().getCoverImg())
                        .address(userOptional.get().getAddress())
                        .build();
                res.setData(userResponse);
                res.setMessage("fetch data success!");
                res.setStatus(true);
            }
            return ResponseEntity.ok(res);
        } catch (Exception exception) {
            res.setData(null);
            res.setMessage("fetch data false!");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
    private ResponseEntity<?> handleException(Exception exception) {
        res.setData(null);
        res.setMessage("An error occurred: " + exception.getMessage());
        res.setStatus(false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    private UserResponse userResponse(Optional<User> userOptional) {
        return UserResponse.builder()
                .id(userOptional.get().getId())
                .email(userOptional.get().getEmail())
                .phoneNum(userOptional.get().getPhoneNumber())
                .username(userOptional.get().getUsername())
                .role(userOptional.get().getRole())
                .coverImg(userOptional.get().getCoverImg())
                .address(userOptional.get().getAddress())
                .build();
    }

}
