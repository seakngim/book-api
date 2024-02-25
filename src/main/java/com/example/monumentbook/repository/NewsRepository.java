package com.example.monumentbook.repository;

import com.example.monumentbook.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
    Page<News> findByDeleteFalse(Pageable pageable);
}
