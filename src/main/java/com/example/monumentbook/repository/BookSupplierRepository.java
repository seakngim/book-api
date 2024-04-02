package com.example.monumentbook.repository;

import com.example.monumentbook.model.BookSupplier;
import com.example.monumentbook.model.Category;
import com.example.monumentbook.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSupplierRepository  extends JpaRepository<BookSupplier, Integer> {
    List<BookSupplier> findAllBySupplier(Supplier supplier);
}
