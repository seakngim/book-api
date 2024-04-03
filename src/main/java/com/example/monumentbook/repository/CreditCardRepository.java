package com.example.monumentbook.repository;

import com.example.monumentbook.model.CreditCard;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.dto.CreditCardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {
    Optional<CreditCard> findByUserId(long id);
}
