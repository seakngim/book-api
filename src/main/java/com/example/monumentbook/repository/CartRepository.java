package com.example.monumentbook.repository;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.Cart;
import com.example.monumentbook.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Page<Cart> findByDeletedFalse(Pageable pageable);

    Page<Cart> findByUserIdIdAndDeletedFalse(Long userId, Pageable pageable);

    Optional<Cart> findByUserIdAndBookIdAndDeletedFalse(User user, Book book);
}
