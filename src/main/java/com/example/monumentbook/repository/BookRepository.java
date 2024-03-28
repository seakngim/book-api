package com.example.monumentbook.repository;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.responses.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book getByIsbn(String isbn);
    Optional<Book> findBookByTitle(String string);
    Page<Book> findByDeleteFalse(Pageable pageable);
    Page<Book> findByDeleteFalseAndOfTheWeekTrue(Pageable pageable);
    Page<Book> findByDeleteFalseAndBestSellTrue(Pageable pageable);
    Page<Book> findByDeleteFalseAndNewArrivalTrue(Pageable pageable);
//    List<Book> findByDeleteFalseAndTitleOrDescriptionOrIsbn(String string);

}
