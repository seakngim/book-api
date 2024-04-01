package com.example.monumentbook.repository;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.responses.BookResponse;
import com.example.monumentbook.model.responses.BookSearchResult;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select\n" +
            "\tb.id as book_id,\n" +
            "\tb.title,\n" +
            "\tb.price,\n" +
            "\tb.description,\n" +
            "\tb.cover_img,\n" +
            "\ta.name,\n" +
            "\tc.name\n" +
            "\t\n" +
            "from\n" +
            "\t`book_tb` b\n" +
            "inner join `category_book_tb` cb on\n" +
            "\tcb.book_id = b.id\n" +
            "inner join author_book_tb ab on\n" +
            "\tab.book_id = b.id \n" +
            "inner join `authors_tb` a on\n" +
            "\ta.author_id = ab.authors_id \n" +
            "inner join `category_tb` c on\n" +
            "\tc.id = cb.category_id \n" +
            "\t\n" +
            "where \n"+
            "\t(b.title like %:filter%\n" +
            "\tor\n" +
            "\tc.name like %:filter%\n" +
            "\tor\n" +
            "\tb.isbn like %:filter%\n" +
            "\tor\n" +
            "\ta.name like %:filter%)\n" +
            "\tand\n" +
            "\tb.deleted = false", nativeQuery = true)
    List<?> searchAll(String filter);
}
