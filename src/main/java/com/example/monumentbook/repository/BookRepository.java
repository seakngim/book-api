package com.example.monumentbook.repository;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.responses.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book getByIsbn(String isbn);
}
