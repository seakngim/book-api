package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.responses.BookResponse;
import com.example.monumentbook.model.responses.BookSearchResult;
import com.example.monumentbook.repository.AuthorBookRepository;
import com.example.monumentbook.repository.SearchRepository;
import com.example.monumentbook.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;


    @Override
    public ResponseEntity<?> search(String filter) {
        List<?> tuples = searchRepository.searchAll(filter);
        List<BookSearchResult> results = mapTuplesToBookSearchResults(tuples);
        return ResponseEntity.ok(results);
//        return ResponseEntity.ok(searchRepository.searchAll(filter));
    }

    private List<BookSearchResult> mapTuplesToBookSearchResults(List<?> tuples) {
        List<BookSearchResult> results = new ArrayList<>();
        for (Object tuple : tuples) {
            Object[] tupleArray = (Object[]) tuple;
            Integer id = (Integer) tupleArray[0];
            String title = (String) tupleArray[1];
            int price = (int) tupleArray[2];
            String description = (String) tupleArray[3];
            String cover_img = (String) tupleArray[4];
            String authorName = (String) tupleArray[5];
            String categoryName = (String) tupleArray[6];

            BookSearchResult result = new BookSearchResult();
            result.setId(id);
            result.setTitle(title);
            result.setPrice(price);
            result.setCoverImg(cover_img);
            result.setAuthorName(authorName);
            result.setDescription(description);
            result.setCategoryName(categoryName);

            results.add(result);
        }
        return results;
    }

}
