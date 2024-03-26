package com.example.monumentbook.repository;

import com.example.monumentbook.model.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
    Page<Vendor> findByDeletedFalse(Pageable pageable);
    Optional<Vendor> findByIdAndDeletedFalse(Integer id);
}
