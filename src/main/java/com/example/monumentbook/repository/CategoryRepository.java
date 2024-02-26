package com.example.monumentbook.repository;


import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByDeletedFalse(Pageable pageable);
}
