package com.example.monumentbook.repository;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.Bookmarks;
import com.example.monumentbook.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmarks,Integer> {
    Optional<Bookmarks> findByUserIdAndBookIdAndDeletedFalse(User currentUser, Book book);

    Page<Bookmarks> findByUserIdIdAndDeletedFalse(long id, Pageable pageable);
}
