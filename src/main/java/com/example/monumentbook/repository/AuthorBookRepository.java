package com.example.monumentbook.repository;

import com.example.monumentbook.model.Author;
import com.example.monumentbook.model.AuthorBook;
import com.example.monumentbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
        List<AuthorBook> findAllByBook(Book book);
        List<AuthorBook> findAllByAuthors(Author author);
@Query(value = "select\n" +
        "\tb.id as book_id,\n" +
        "\tb.title,\n" +
        "\ta.name,\n" +
        "\tc.name\n" +
        "\t\n" +
        "from\n" +
        "\t`_book` b\n" +
        "inner join `_category_book` cb on\n" +
        "\tcb.book_id = b.id\n" +
        "inner join author_book ab on\n" +
        "\tab.book_id = b.id \n" +
        "inner join authors a on\n" +
        "\ta.author_id = ab.authors_id \n" +
        "inner join `_category` c on\n" +
        "\tc.id = cb.category_id \n" +
        "\t\n" +
        "where \n" +
        "\tb.title like %:filter%\n" +
        "\t\n" +
        "\tor \n" +
        "\tc.name like %:filter%\n" +
        "\tor \n" +
        "\tb.isbn like %:filter%\n" +
        "\tor \n" +
        "\ta.name like %:filter%", nativeQuery = true)
        List<?> searchAll(String filter);
}
