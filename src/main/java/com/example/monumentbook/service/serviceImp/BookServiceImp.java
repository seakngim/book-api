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
        List<Book> bookList = bookRepository.findAll();
        List<BookResponse> bookResponsesList = new ArrayList<>();
        for(Book book: bookList){
            List<AuthorBook> authorBookList = authorBookRepository.findAllByBook(book);
            List<AuthorDto> authorListName = new ArrayList<>();
            for (AuthorBook authorBook : authorBookList){
                Optional<Author> author = authorRepository.findById(authorBook.getAuthors().getId());
                if (author.isPresent()){
                    AuthorDto authorDto = new AuthorDto();
                        authorDto.setName(author.get().getName());
                        authorDto.setDescription(author.get().getName());
                        authorDto.setImage(author.get().getImage());
                    authorListName.add(authorDto);
                }
            }
            BookResponse bookResponse= BookResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .price(book.getPrice())
                    .qty(book.getQty())
                    .publisher(book.getPublisher())
                    .coverImg(book.getCoverImg())
                    .categories(book.getCategories().toDto())
                    .author(authorListName)
                    .build();
            bookResponsesList.add(bookResponse);
        }
        return ResponseEntity.ok(ApiResponse.<List<BookResponse>>builder()
                .message("success fetch Book")
                .status(HttpStatus.OK)
                .payload(bookResponsesList)
                .build());
    }

    @Override
    public ResponseEntity<?> findBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            List<AuthorBook> authorBookList = authorBookRepository.findAllByBook(book.get());
            List<AuthorDto> authorListName = new ArrayList<AuthorDto>();
            for(AuthorBook authorBook: authorBookList){
                Optional<Author> author = authorRepository.findById(authorBook.getAuthors().getId());
                if (author.isPresent()){
                    AuthorDto authorDto = new AuthorDto();
                    authorDto.setName(author.get().getName());
                    authorDto.setDescription(author.get().getName());
                    authorDto.setImage(author.get().getImage());
                    authorListName.add(authorDto);
                }
            }
            BookResponse bookResponse= BookResponse.builder()
                    .id(book.get().getId())
                    .title(book.get().getTitle())
                    .description(book.get().getDescription())
                    .price(book.get().getPrice())
                    .publisher(book.get().getPublisher())
                    .coverImg(book.get().getCoverImg())
                    .categories(book.get().getCategories().toDto())
                    .author(authorListName)
                    .build();
            return ResponseEntity.ok(ApiResponse.<BookResponse>builder()
                    .message("Success: Book found with ID " + id)
                    .status(HttpStatus.OK)
                    .payload(bookResponse)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Book>builder()
                            .message("Fail: Book not found with ID " + id)
                            .status(HttpStatus.NOT_FOUND)
                            .build());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveBook(BookRequest book) {

        Optional<BookCategory> category = categoryRepository.findById(book.getCategoryId());
        Book bookObj = null;
        if (category.isPresent()) {
            bookObj = Book.builder()
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .publishDate(book.getPublisherDate())
                    .isbn(book.getIsbn())
                    .coverImg(book.getCoverImg())
                    .publisher(book.getPublisher())
                    .categories(category.get())
                    .build();
        }
        bookRepository.save(bookObj);

        for(Integer author: book.getAuthorsId()){
            Optional<Author> authors = authorRepository.findById(author);
            if(authors.isPresent()){
                AuthorBook authorBook = AuthorBook.builder()
                        .authors(authors.get())
                        .book(bookObj)
                        .build();
                authorBookRepository.save(authorBook);
            }
        }
        List<AuthorBook> authorBookList = authorBookRepository.findAllByBook(bookObj);

        List<AuthorDto> authorListName = new ArrayList<>();
        for (AuthorBook authorBook : authorBookList){
            Optional<Author> author = authorRepository.findById(authorBook.getAuthors().getId());
            if (author.isPresent()){
                AuthorDto authorDto = new AuthorDto();
                authorDto.setName(author.get().getName());
                authorDto.setDescription(author.get().getName());
                authorDto.setImage(author.get().getImage());
                authorListName.add(authorDto);
            }
        }

        BookResponse bookResponse= BookResponse.builder()
                .id(bookObj.getId())
                .title(bookObj.getTitle())
                .description(bookObj.getDescription())
                .price(bookObj.getPrice())
                .qty(bookObj.getQty())
                .publisher(bookObj.getPublisher())
                .coverImg(bookObj.getCoverImg())
                .categories(bookObj.getCategories().toDto())
                .author(authorListName)
                .build();
        return ResponseEntity.ok(ApiResponse.<BookResponse>builder()
                .message("Success: Book add ")
                .status(HttpStatus.OK)
                .payload(bookResponse)
                .build());
    }




}
