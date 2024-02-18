package com.example.monumentbook.repository;

import com.example.monumentbook.model.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerProduct, Integer> {
}
