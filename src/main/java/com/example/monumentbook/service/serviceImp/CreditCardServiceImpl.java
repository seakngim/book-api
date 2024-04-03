package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.CreditCard;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.requests.CreditCardRequest;
import com.example.monumentbook.repository.CreditCardRepository;
import com.example.monumentbook.repository.UserRepository;
import com.example.monumentbook.service.CreditCardService;
import com.example.monumentbook.utilities.response.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> addCard(CreditCardRequest creditCardRequest) {
        System.out.println("11");
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            Optional<User> userOptional = userRepository.findById((int) currentUser.getId());
            System.out.println(userOptional+"12312");
            CreditCard creditCard = CreditCard.builder()
                    .fullName(creditCardRequest.getFullName())
                    .cardNumber(creditCardRequest.getCardNumber())
                    .address(creditCardRequest.getAddress())
                    .cvv(creditCardRequest.getCvv())
                    .city(creditCardRequest.getCity())
                    .expiryMonth(creditCardRequest.getExpiryMonth())
                    .id(creditCardRequest.getExpiryYear())
                    .expiryYear(creditCardRequest.getExpiryYear())
                    .user(userOptional.get())
                    .build();

            System.out.println(creditCard+ "Service ipm213");
            creditCardRepository.save(creditCard);
            ResponseObject res = new ResponseObject();
            res.setMessage("add Card successful");
            res.setStatus(true);
            res.setData(creditCard);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("add Card successful");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.ok(res);
        }

    }
}
