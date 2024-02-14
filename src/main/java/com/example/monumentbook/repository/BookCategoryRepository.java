package com.example.monumentbook.repository;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    List<BookCategory> findBookCategoryByBook(Book book);
}
