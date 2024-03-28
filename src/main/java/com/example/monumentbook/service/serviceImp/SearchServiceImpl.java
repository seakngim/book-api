package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.repository.AuthorBookRepository;
import com.example.monumentbook.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final AuthorBookRepository authorBookRepository;


    @Override
    public ResponseEntity<?> search(Integer page, Integer size) {
        System.out.println(authorBookRepository.searchAll("the"));
        return ResponseEntity.ok(authorBookRepository.searchAll("the")) ;
    }
}
