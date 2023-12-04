package com.example.monumentbook.repository;

import com.example.monumentbook.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<BookCategory , Integer> {
}
