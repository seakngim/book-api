package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Author;
import com.example.monumentbook.model.AuthorBook;
import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.requests.AuthorRequest;
import com.example.monumentbook.model.responses.AuthorBookResponse;
import com.example.monumentbook.model.responses.AuthorResponse;
import com.example.monumentbook.repository.AuthorBookRepository;
import com.example.monumentbook.repository.AuthorRepository;
import com.example.monumentbook.repository.BookRepository;
import com.example.monumentbook.service.AuthorService;
import com.example.monumentbook.utilities.response.ResponseObject;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            List<AuthorBook> authorBookList = authorBookRepository.findAllByAuthors(author);
            List<Book> authorBooks = new ArrayList<>();

            for (AuthorBook authorBook : authorBookList) {
                Optional<Book> bookOptional = bookRepository.findById(authorBook.getBook().getId());

                if (bookOptional.isPresent()) {
                    Book book = Book.builder()
                            .id(bookOptional.get().getId())
                            .title(bookOptional.get().getTitle())
//                            .categories(bookOptional.get().getCategories())
                            .isbn(bookOptional.get().getIsbn())
                            .coverImg(bookOptional.get().getCoverImg())
                            .build();
                    authorBooks.add(book);
                }
            }

            AuthorResponse authorResponse = AuthorResponse.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .description(author.getDescription())
                    .image(author.getImage())
                    .date(author.getDate())
                    .book(authorBooks)
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
    public ResponseEntity<?> getAll() {
        List<Author> authorResponseList = authorRepository.findAll();
        List<AuthorResponse> authors = new ArrayList<>();
        for(Author author: authorResponseList){
            List<AuthorBook> authorBookList = authorBookRepository.findAllByAuthors(author);
            List<Book> authorBooks = new ArrayList<>();
            for(AuthorBook authorBook : authorBookList){
                Optional<Book> bookOptional = bookRepository.findById(authorBook.getBook().getId());
                if(bookOptional.isPresent()){
                    Book book = Book.builder()
                            .id(bookOptional.get().getId())
                            .title(bookOptional.get().getTitle())
//                            .categories(bookOptional.get().getCategories())
                            .isbn(bookOptional.get().getIsbn())
                            .coverImg(bookOptional.get().getCoverImg())
                            .build();
                    authorBooks.add(book);
                }
            }
            AuthorResponse authorResponse = AuthorResponse.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .description(author.getDescription())
                    .image(author.getImage())
                    .date(author.getDate())
                    .build();
            authors.add(authorResponse);
        }
        res.setMessage("Author add success!");
        res.setStatus(true);
        res.setData(authors);
        return ResponseEntity.ok(res);
    }
}
