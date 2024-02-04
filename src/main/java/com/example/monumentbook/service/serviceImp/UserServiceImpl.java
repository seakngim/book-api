package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.repository.UserRepository;
import com.example.monumentbook.service.UserService;
import com.example.monumentbook.utilities.response.EmptyObject;
import com.example.monumentbook.utilities.response.Message;
import com.example.monumentbook.utilities.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final UserSubjectRepository userSubjectRepository;
//    private final SubjectRepository subjectRepository;
//    private final UserShiftRepository userShiftRepository;
//    private final ShiftRepository shiftRepository;
    ResponseObject res = new ResponseObject();
    Message message = new Message();
    EmptyObject emptyObject = new EmptyObject();
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
