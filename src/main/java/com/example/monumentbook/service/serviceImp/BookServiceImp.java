package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Author;
import com.example.monumentbook.model.AuthorBook;
import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.BookCategory;
import com.example.monumentbook.model.dto.AuthorDto;
import com.example.monumentbook.model.requests.BookRequest;
import com.example.monumentbook.model.responses.ApiResponse;
import com.example.monumentbook.model.responses.BookResponse;
import com.example.monumentbook.repository.AuthorBookRepository;
import com.example.monumentbook.repository.AuthorRepository;
import com.example.monumentbook.repository.BookRepository;
import com.example.monumentbook.repository.CategoryRepository;
import com.example.monumentbook.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {
 private final BookRepository bookRepository;
 private final AuthorRepository authorRepository;
 private final CategoryRepository categoryRepository;
 private final AuthorBookRepository authorBookRepository;

    public BookServiceImp(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, AuthorBookRepository authorBookRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.authorBookRepository = authorBookRepository;
    }


    @Override
    public ResponseEntity<?> findAllBook() {
//        List<Book> bookList = bookRepository.findAll();
//        List<BookResponse> bookResponsesList = new ArrayList<>();
//        for(Book book: bookList){
//            List<AuthorBook> authorBookList = authorBookRepository.findAllByBook(book);
//            List<AuthorDto> authorListName = new ArrayList<>();
//            for (AuthorBook authorBook : authorBookList){
//                Optional<Author> author = authorRepository.findById(authorBook.getAuthors().getId());
//                if (author.isPresent()){
//                    AuthorDto authorDto = new AuthorDto();
//                        authorDto.setName(author.get().getName());
//                        authorDto.setDescription(author.get().getName());
//                        authorDto.setImage(author.get().getImage());
//                    authorListName.add(authorDto);
//                }
//            }
//            BookResponse bookResponse= BookResponse.builder()
//                    .id(book.getId())
//                    .title(book.getTitle())
//                    .description(book.getDescription())
//                    .publisher(book.getPublisher())
//                    .coverImg(book.getCoverImg())
//                    .categories(book.getCategories().toDto())
//                    .author(authorListName)
//                    .build();
//            bookResponsesList.add(bookResponse);
//        }
//        return ResponseEntity.ok(ApiResponse.<List<BookResponse>>builder()
//                .message("success fetch Book")
//                .status(HttpStatus.OK)
//                .payload(bookResponsesList)
//                .build());
        return null;
    }

    @Override
    public ResponseEntity<?> findBookById(Integer id) {
//        Optional<Book> book = bookRepository.findById(id);
//
//        if (book.isPresent()) {
//            List<AuthorBook> authorBookList = authorBookRepository.findAllByBook(book.get());
//            List<AuthorDto> authorListName = new ArrayList<AuthorDto>();
//            for(AuthorBook authorBook: authorBookList){
//                Optional<Author> author = authorRepository.findById(authorBook.getAuthors().getId());
//                if (author.isPresent()){
//                    AuthorDto authorDto = new AuthorDto();
//                    authorDto.setName(author.get().getName());
//                    authorDto.setDescription(author.get().getName());
//                    authorDto.setImage(author.get().getImage());
//                    authorListName.add(authorDto);
//                }
//            }
//            BookResponse bookResponse= BookResponse.builder()
//                    .id(book.get().getId())
//                    .title(book.get().getTitle())
//                    .description(book.get().getDescription())
//                    .publisher(book.get().getPublisher())
//                    .coverImg(book.get().getCoverImg())
//                    .categories(book.get().getCategories().toDto())
//                    .author(authorListName)
//                    .build();
//            return ResponseEntity.ok(ApiResponse.<BookResponse>builder()
//                    .message("Success: Book found with ID " + id)
//                    .status(HttpStatus.OK)
//                    .payload(bookResponse)
//                    .build());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(ApiResponse.<Book>builder()
//                            .message("Fail: Book not found with ID " + id)
//                            .status(HttpStatus.NOT_FOUND)
//                            .build());
//        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveBook(BookRequest bookRequest) {
        Book book = Book.builder()
                .isbn(bookRequest.getIsbn())
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .coverImg(bookRequest.getCoverImg())
                .publishDate(bookRequest.getPublisherDate())
                .delete(false)
                .build();
        bookRepository.save(book);
        return ResponseEntity.ok(book);

    }




}
