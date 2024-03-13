package com.example.monumentbook.repository;

import com.example.monumentbook.model.Author;
import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.BookCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Page<Author> findByDeletedFalse(Pageable pageable);

    Page<Author> findByDeletedFalseAndStatusTrue(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Author a SET a.status = false WHERE a.id <> :authorId")
    void setAllOtherRowsToFalse(@Param("authorId") Integer authorId);
}
