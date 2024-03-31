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

//    Book getByIsbn(String isbn);
//    Optional<Book> findBookByTitle(String string);
    Page<Book> findByDeletedFalse(Pageable pageable);
    Page<Book> findByDeletedFalseAndOfTheWeekTrue(Pageable pageable);
    Page<Book> findByDeletedFalseAndBestSellTrue(Pageable pageable);
    Page<Book> findByDeletedFalseAndNewArrivalTrue(Pageable pageable);

    Optional<Book> findByIdAndDeletedFalse(Integer id);

}
