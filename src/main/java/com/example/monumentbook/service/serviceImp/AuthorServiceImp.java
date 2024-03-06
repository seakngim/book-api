package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Author;
import com.example.monumentbook.model.AuthorBook;
import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.requests.AuthorRequest;
import com.example.monumentbook.model.responses.ApiResponse;
import com.example.monumentbook.model.responses.AuthorResponse;
import com.example.monumentbook.repository.AuthorBookRepository;
import com.example.monumentbook.repository.AuthorRepository;
import com.example.monumentbook.repository.BookRepository;
import com.example.monumentbook.service.AuthorService;
import com.example.monumentbook.utilities.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImp implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorBookRepository authorBookRepository;
    private final BookRepository bookRepository;
    ResponseObject res = new ResponseObject();
    @Override
    public ResponseEntity<?> add(AuthorRequest authorRequest) {
        Author author = Author.builder()
                .name(authorRequest.getName())
                .description(authorRequest.getDescription())
                .image(authorRequest.getImage())
                .date(LocalDate.now())
                .build();
        authorRepository.save(author);
        res.setMessage("Author add success!");
        res.setStatus(true);
        res.setData(author);
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<?> getById(Integer id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            List<BookDto> books = bookFlags(author);
            AuthorResponse authorResponse = AuthorResponse.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .description(author.getDescription())
                    .image(author.getImage())
                    .date(author.getDate())
                    .books(books)
                    .build();
            ResponseObject res = new ResponseObject();
            res.setMessage("Author retrieval success!");
            res.setStatus(true);
            res.setData(authorResponse);
            return ResponseEntity.ok(res);
        } else {
            ResponseObject res = new ResponseObject();
            res.setMessage("Author not found");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }
    @Override
    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<Author> pageResult = authorRepository.findByDeletedFalse(pageable);
            List<AuthorResponse> authors = new ArrayList<>();
            for(Author author: pageResult.getContent()){
                List<BookDto> books = bookFlags(author);
                AuthorResponse authorResponse = AuthorResponse.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .description(author.getDescription())
                        .image(author.getImage())
                        .books(books)
                        .date(author.getDate())
                        .build();
                authors.add(authorResponse);
            }
            if (!authors.isEmpty()) {
                ApiResponse res = new ApiResponse(true, "Fetch books successful!", authors, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
                return ResponseEntity.ok(res);
            } else {
                ApiResponse res = new ApiResponse(false, "No books found!", null, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            ApiResponse res = new ApiResponse(false, "Error fetching authors!", null, 0, 0, 0, 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

    }

    @Override
    public ResponseEntity<?> update(Integer id, AuthorRequest authorRequest) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = Author.builder()
                    .id(authorOptional.get().getId())
                    .name(authorRequest.getName() != null ? authorRequest.getName() : authorOptional.get().getName())
                    .description(authorRequest.getDescription() != null ? authorRequest.getDescription():authorOptional.get().getImage())
                    .image(authorRequest.getImage() != null ? authorRequest.getImage():authorOptional.get().getImage() )
                    .date(authorOptional.get().getDate())
                    .build();
            authorRepository.save(author);
            List<BookDto> books = bookFlags(authorOptional.get());
            AuthorResponse authorResponse = AuthorResponse.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .description(author.getDescription())
                    .image(author.getImage())
                    .date(author.getDate())
                    .books(books)
                    .build();
            ResponseObject res = new ResponseObject();
            res.setMessage("Author retrieval success!");
            res.setStatus(true);
            res.setData(authorResponse);
            return ResponseEntity.ok(res);
        } else {
            ResponseObject res = new ResponseObject();
            res.setMessage("Author not found");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @Override
    public ResponseEntity<?> deleted(Integer id) {
        try {
            Optional<Author> authorOptional = authorRepository.findById(id);
            if (authorOptional.isPresent()) {
                Author author = Author.builder()
                        .id(authorOptional.get().getId())
                        .name(authorOptional.get().getName())
                        .description(authorOptional.get().getImage())
                        .image(authorOptional.get().getImage() )
                        .date(authorOptional.get().getDate())
                        .deleted(true)
                        .build();
                authorRepository.save(author);
                List<BookDto> books = bookFlags(authorOptional.get());
                AuthorResponse authorResponse = AuthorResponse.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .description(author.getDescription())
                        .image(author.getImage())
                        .date(author.getDate())
                        .books(books)
                        .build();
                ResponseObject res = new ResponseObject();
                res.setMessage("Author retrieval success!");
                res.setStatus(true);
                res.setData(authorResponse);
                return ResponseEntity.ok(res);
            } else {
                ResponseObject res = new ResponseObject();
                res.setMessage("Author not found");
                res.setStatus(false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            ResponseObject res = new ResponseObject();
            res.setMessage("Author not found");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @Override
    public ResponseEntity<?> getFeature(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<Author> pageResult = authorRepository.findByDeletedFalseAndStatusTrue(pageable);
            List<AuthorResponse> authors = new ArrayList<>();

            for (Author author : pageResult.getContent()) {
                List<BookDto> books = bookFlags(author);
                AuthorResponse authorResponse = AuthorResponse.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .description(author.getDescription())
                        .quote(author.getQuote())
                        .image(author.getImage())
                        .books(books)
                        .date(author.getDate())
                        .build();
                authors.add(authorResponse);
            }

//            List<Map<String, Object>> featureAuthors = createFeatureAuthorObject(authors);

            if (!authors.isEmpty()) {
                ApiResponse res = new ApiResponse(true, "Fetch authors successful!", authors, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
                return ResponseEntity.ok(res);
            } else {
                ApiResponse res = new ApiResponse(false, "No authors found!", null, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        } catch (Exception e) {
            ApiResponse res = new ApiResponse(false, "Error fetching authors!", null, 0, 0, 0, 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

//    private List<Map<String, Object>> createFeatureAuthorObject(List<AuthorResponse> authors) {
//        List<Map<String, Object>> result = new ArrayList<>();
//
//        for (AuthorResponse author : authors) {
//            if (author != null) {
//                Map<String, Object> authorMap = Map.of(
//                        "authorquote", author.getQuote(),
//                        "authorname", author.getName(),
//                        "authorimg", author.getImage(),
//                        "authordesc", author.getDescription(),
//                        "authorbooks", author.getBooks().stream().map(BookDto::getCoverImg).collect(Collectors.toList())
//                );
//                result.add(authorMap);
//            }
//        }
//
//        return result;
//    }


    private  List<BookDto> bookFlags(Author author){
        List<AuthorBook> authorBookList = authorBookRepository.findAllByAuthors(author);
        List<BookDto> authorBooks = new ArrayList<>();
        for(AuthorBook authorBook : authorBookList){
            Optional<Book> bookOptional = bookRepository.findById(authorBook.getBook().getId());
            if(bookOptional.isPresent()){
                BookDto book = BookDto.builder()
                        .id(bookOptional.get().getId())
                        .title(bookOptional.get().getTitle())
                        .isbn(bookOptional.get().getIsbn())
                        .coverImg(bookOptional.get().getCoverImg())
                        .description(bookOptional.get().getDescription())
                        .build();
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }
}
