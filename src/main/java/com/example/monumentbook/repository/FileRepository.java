package com.example.monumentbook.repository;

import com.example.monumentbook.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository  extends JpaRepository<File, String> {
}
