package com.example.monumentbook.repository;

import com.example.monumentbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelFileUploadRepository extends JpaRepository<Book, Integer> {

}
