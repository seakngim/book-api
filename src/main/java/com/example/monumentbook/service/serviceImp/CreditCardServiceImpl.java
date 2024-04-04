package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.CreditCard;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.requests.CreditCardRequest;
import com.example.monumentbook.model.responses.CreditCardResponse;
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
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            Optional<User> userOptional = userRepository.findById((int) currentUser.getId());
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
            creditCardRepository.save(creditCard);
            CreditCardResponse cardResponse = cardResponse(creditCard);
            ResponseObject res = new ResponseObject();
            res.setMessage("add Card successful");
            res.setStatus(true);
            res.setData(cardResponse);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("add Card successful");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.ok(res);
        }

    }

    @Override
    public ResponseEntity<?> deleteCard(Integer id) {
        try {
            Optional<CreditCard> creditCard = creditCardRepository.findById(id);
            if(creditCard.isPresent()){
                creditCardRepository.delete(creditCard.get());
            }
        }catch (Exception e){
        }
        ResponseObject res = new ResponseObject();
        res.setMessage("Delete Card successful");
        res.setStatus(true);
        res.setData(null);
        return ResponseEntity.ok(res);
    }


    @Override
    public ResponseEntity<?> updateCard(Integer id, CreditCardRequest creditCardRequest) {
        try {
            ResponseObject res = new ResponseObject();
            Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
            if(creditCardOptional.isPresent()){
                CreditCard creditCard = CreditCard.builder()
                        .id(creditCardOptional.get().getId())
                        .fullName(creditCardRequest.getFullName())
                        .cardNumber(creditCardRequest.getCardNumber())
                        .address(creditCardRequest.getAddress())
                        .cvv(creditCardRequest.getCvv())
                        .city(creditCardRequest.getCity())
                        .expiryMonth(creditCardRequest.getExpiryMonth())
                        .expiryYear(creditCardRequest.getExpiryYear())
                        .user(creditCardOptional.get().getUser())
                        .build();
                creditCardRepository.save(creditCard);
                CreditCardResponse cardResponse = cardResponse(creditCard);
                res.setMessage("Delete Card successful");
                res.setStatus(true);
                res.setData(cardResponse);
            }
            return ResponseEntity.ok(res);
        }catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("update Card successful");
            res.setStatus(true);
            res.setData(res);
            return ResponseEntity.ok(res);
        }


    }

    @Override
    public ResponseEntity<?> findCardById(Integer id) {
        try {
            ResponseObject res = new ResponseObject();
            Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
            if(creditCardOptional.isPresent()){
                CreditCard creditCard = CreditCard.builder()
                        .id(creditCardOptional.get().getId())
                        .fullName(creditCardOptional.get().getFullName())
                        .cardNumber(creditCardOptional.get().getCardNumber())
                        .address(creditCardOptional.get().getAddress())
                        .cvv(creditCardOptional.get().getCvv())
                        .city(creditCardOptional.get().getCity())
                        .expiryMonth(creditCardOptional.get().getExpiryMonth())
                        .expiryYear(creditCardOptional.get().getExpiryYear())
                        .user(creditCardOptional.get().getUser())
                        .build();
                CreditCardResponse cardResponse = cardResponse(creditCard);
                res.setMessage("fide Card found with Id "+cardResponse.getId());
                res.setStatus(true);
                res.setData(cardResponse);
            }
            return ResponseEntity.ok(res);
        }catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("find Card not found!");
            res.setStatus(false);
            res.setData(res);
            return ResponseEntity.ok(res);
        }
    }

    private CreditCardResponse cardResponse(CreditCard creditCard){
        System.out.println(creditCard.getId());
           return CreditCardResponse.builder()
                   .id(creditCard.getId())
                   .fullName(creditCard.getFullName())
                   .date(creditCard.getDate())
                   .cardNumber(creditCard.getCardNumber())
                   .cvv(creditCard.getCvv())
                   .expiryYear(creditCard.getExpiryYear())
                   .expiryMonth(creditCard.getExpiryMonth())
                   .date(creditCard.getDate())
                   .address(creditCard.getAddress())
                   .city(creditCard.getCity())
                   .user(creditCard.getUser().toDto())
                   .build();
    }

}
