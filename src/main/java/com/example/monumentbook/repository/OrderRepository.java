package com.example.monumentbook.repository;

import com.example.monumentbook.model.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {

    Page<CustomerOrder> findByUserIdIdAndDeletedFalse(long id, Pageable pageable);

}
