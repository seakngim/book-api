package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.*;
import com.example.monumentbook.model.dto.AuthorDto;
import com.example.monumentbook.model.dto.CategoryDto;
import com.example.monumentbook.model.requests.BookRequest;
import com.example.monumentbook.model.requests.ProductRequest;
import com.example.monumentbook.model.responses.BookResponse;
import com.example.monumentbook.repository.*;
import com.example.monumentbook.service.BookService;
import com.example.monumentbook.utilities.response.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
 private final BookCategoryRepository bookCategoryRepository;
 private final VendorRepository vendorRepository;
 private final CustomerRepository customerRepository;

 ResponseObject res = new ResponseObject();

    public BookServiceImp(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, AuthorBookRepository authorBookRepository, BookCategoryRepository bookCategoryRepository, VendorRepository vendorRepository, CustomerRepository customerRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.authorBookRepository = authorBookRepository;
        this.bookCategoryRepository = bookCategoryRepository;
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<?> findAllBook() {
        try {
            List<BookResponse> bookObj = new ArrayList<>();
            List<Book> bookList = bookRepository.findAll();
            for (Book book: bookList){
                Optional<Book> bookOptional = bookRepository.findById(book.getId());
                if (bookOptional.isPresent() && !bookOptional.get().isDelete()){
                    List<CategoryDto> categoryObj = new ArrayList<>();
                    List<BookCategory> categoryByBook = bookCategoryRepository.findBookCategoryByBook(bookOptional.get());
                    for (BookCategory bookCategory: categoryByBook){

                        Optional<Category> categoryOptional = categoryRepository.findById(bookCategory.getCategory().getId());
                        if (categoryOptional.isPresent()){
                            CategoryDto categoryDto = CategoryDto.builder()
                                    .id(categoryOptional.get().getId())
                                    .name(categoryOptional.get().getName())
                                    .description(categoryOptional.get().getDescription())
                                    .build();
                            categoryObj.add(categoryDto);
                        }

                    }
                    List<AuthorDto> authorObj = new ArrayList<>();
                    List<AuthorBook> categoryByAuthor = authorBookRepository.findAllByBook(bookOptional.get());
                    for (AuthorBook authorBook :categoryByAuthor){
                        Optional<Author> authorOptional = authorRepository.findById(authorBook.getAuthors().getId());
                        if (authorOptional.isPresent()){
                            AuthorDto authorDto = AuthorDto.builder()
                                    .id(authorOptional.get().getId())
                                    .name(authorOptional.get().getName())
                                    .description(authorOptional.get().getDescription())
                                    .image(authorOptional.get().getImage())
                                    .build();
                            authorObj.add(authorDto);
                        }

                    }
                    BookResponse bookResponse = BookResponse.builder()
                            .id(bookOptional.get().getId())
                            .title(bookOptional.get().getTitle())
                            .description(bookOptional.get().getDescription())
                            .isbn(bookOptional.get().getIsbn())
                            .coverImg(bookOptional.get().getCoverImg())
                            .publisher(bookOptional.get().getPublisher())
                            .publishDate(bookOptional.get().getPublishDate())
                            .price(bookOptional.get().getPrice())
                            .qty(bookOptional.get().getQty())
                            .author(authorObj)
                            .categories(categoryObj)
                            .build();
                    bookObj.add(bookResponse);

                }
            }
            if (!bookObj.isEmpty()) {
                res.setStatus(true);
                res.setMessage("Fetch books successful!");
                res.setData(bookObj);
                return ResponseEntity.ok(res);
            } else {
                res.setStatus(false);
                res.setMessage("No books found!");
                res.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> findBookById(Integer id) {
        try {
            List<BookResponse> bookObj = new ArrayList<>();
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()){
                List<CategoryDto> categoryObj = new ArrayList<>();
                List<BookCategory> categoryByBook = bookCategoryRepository.findBookCategoryByBook(bookOptional.get());
                for (BookCategory bookCategory: categoryByBook){

                    Optional<Category> categoryOptional = categoryRepository.findById(bookCategory.getCategory().getId());
                    if (categoryOptional.isPresent()){
                        CategoryDto categoryDto = CategoryDto.builder()
                                .id(categoryOptional.get().getId())
                                .name(categoryOptional.get().getName())
                                .description(categoryOptional.get().getDescription())
                                .build();
                        categoryObj.add(categoryDto);
                    }

                }
                List<AuthorDto> authorObj = new ArrayList<>();
                List<AuthorBook> categoryByAuthor = authorBookRepository.findAllByBook(bookOptional.get());
                for (AuthorBook authorBook :categoryByAuthor){
                    Optional<Author> authorOptional = authorRepository.findById(authorBook.getAuthors().getId());
                    if (authorOptional.isPresent()){
                        AuthorDto authorDto = AuthorDto.builder()
                                .id(authorOptional.get().getId())
                                .name(authorOptional.get().getName())
                                .description(authorOptional.get().getDescription())
                                .image(authorOptional.get().getImage())
                                .build();
                        authorObj.add(authorDto);
                    }

                }
                BookResponse bookResponse = BookResponse.builder()
                        .id(bookOptional.get().getId())
                        .title(bookOptional.get().getTitle())
                        .description(bookOptional.get().getDescription())
                        .isbn(bookOptional.get().getIsbn())
                        .coverImg(bookOptional.get().getCoverImg())
                        .publisher(bookOptional.get().getPublisher())
                        .publishDate(bookOptional.get().getPublishDate())
                        .price(bookOptional.get().getPrice())
                        .qty(bookOptional.get().getQty())
                        .author(authorObj)
                        .categories(categoryObj)
                        .build();
                bookObj.add(bookResponse);
                res.setStatus(true);
                res.setMessage("fetch book successful!");
                res.setData(bookObj);
                return ResponseEntity.ok(res);
            }else {
                res.setStatus(false);
                res.setMessage("Book not found!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?> saveBook(BookRequest bookRequest) {
        try {
            Book book = Book.builder()
                    .isbn(bookRequest.getIsbn())
                    .title(bookRequest.getTitle())
                    .description(bookRequest.getDescription())
                    .coverImg(bookRequest.getCoverImg())
                    .publisher(bookRequest.getPublisher())
                    .publishDate(bookRequest.getPublishDate())
                    .price(bookRequest.getPrice())
                    .build();
            bookRepository.save(book);
            List<AuthorBook> authorBookList = new ArrayList<>();
            for (Integer author : bookRequest.getAuthorId()){
                Optional<Author> authorOptional = authorRepository.findById(author);
                if (authorOptional.isPresent()) {
                    AuthorBook authorBook = AuthorBook.builder()
                            .authors(authorOptional.get())
                            .book(book)
                            .build();
                    authorBookList.add(authorBook);
                }
            }
            List<BookCategory> bookCategoryList = new ArrayList<>();
            for (Integer category : bookRequest.getCategoryId()){
                Optional<Category> categoryOptional = categoryRepository.findById(category);
                if (categoryOptional.isPresent()){
                    BookCategory bookCategory = BookCategory.builder()
                            .book(book)
                            .category(categoryOptional.get())
                            .build();
                    bookCategoryList.add(bookCategory);
                }
            }
            bookCategoryRepository.saveAll(bookCategoryList);
            authorBookRepository.saveAll(authorBookList);
            List<CategoryDto> categories = new ArrayList<>();
            for (Integer category: bookRequest.getCategoryId()){
                Optional<Category> categoryOptional = categoryRepository.findById(category);
                    if (categoryOptional.isPresent()){
                        CategoryDto categoryDto = CategoryDto.builder()
                                .id(category)
                                .description(categoryOptional.get().getDescription())
                                .name(categoryOptional.get().getName())
                                .build();
                        categories.add(categoryDto);
                    }
            }
            List<AuthorDto> authorDtos = new ArrayList<>();
            for (Integer author: bookRequest.getAuthorId()){
                Optional<Author> authorOptional = authorRepository.findById(author);
                if (authorOptional.isPresent()){
                    AuthorDto authorDto = AuthorDto.builder()
                            .id(authorOptional.get().getId())
                            .name(authorOptional.get().getName())
                            .description(authorOptional.get().getDescription())
                            .image(authorOptional.get().getImage())
                            .build();
                    authorDtos.add(authorDto);
                }
            }
            BookResponse bookResponse = BookResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .isbn(book.getIsbn())
                    .coverImg(book.getCoverImg())
                    .publisher(book.getPublisher())
                    .publishDate(book.getPublishDate())
                    .author(authorDtos)
                    .categories(categories)
                    .build();
            res.setMessage("book add success!");
            res.setData(bookResponse);
            res.setStatus(true);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> updateBook(BookRequest bookRequest, Integer id) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()){
                Book book = Book.builder()
                        .id(id)
                        .isbn(bookRequest.getIsbn())
                        .title(bookRequest.getTitle())
                        .description(bookRequest.getDescription())
                        .coverImg(bookRequest.getCoverImg())
                        .publisher(bookRequest.getPublisher())
                        .publishDate(bookRequest.getPublishDate())
                        .price(bookRequest.getPrice())
                        .qty(bookOptional.get().getQty())
                        .delete(bookOptional.get().isDelete())
                        .build();
                bookRepository.save(book);
                List<AuthorBook> authorBookList = new ArrayList<>();
                for (Integer author : bookRequest.getAuthorId()){
                    Optional<Author> authorOptional = authorRepository.findById(author);
                    List<AuthorBook> authorBooks = authorBookRepository.findAllByBook(book);
                    for(AuthorBook authorBook : authorBooks){
                        authorBookRepository.deleteById(authorBook.getId());
                    }
                    if (authorOptional.isPresent()) {
                        AuthorBook authorBook = AuthorBook.builder()
                                .authors(authorOptional.get())
                                .book(book)
                                .build();
                        authorBookList.add(authorBook);

                    }
                    authorBookRepository.deleteById(author);
                }
                List<BookCategory> bookCategoryList = new ArrayList<>();
                for (Integer category : bookRequest.getCategoryId()){
                    Optional<Category> categoryOptional = categoryRepository.findById(category);
                    List<BookCategory> bookCategoryList1 = bookCategoryRepository.findBookCategoryByBook(book);
                    for (BookCategory bookCategory : bookCategoryList1){
                        bookCategoryRepository.deleteById(bookCategory.getId());
                    }
                    if (categoryOptional.isPresent()){
                        BookCategory bookCategory = BookCategory.builder()
                                .book(book)
                                .category(categoryOptional.get())
                                .build();
                        bookCategoryList.add(bookCategory);

                    }

                }
                bookCategoryRepository.saveAll(bookCategoryList);
                authorBookRepository.saveAll(authorBookList);
                List<CategoryDto> categories = new ArrayList<>();
                for (Integer category: bookRequest.getCategoryId()){
                    Optional<Category> categoryOptional = categoryRepository.findById(category);
                    if (categoryOptional.isPresent()){
                        CategoryDto categoryDto = CategoryDto.builder()
                                .id(category)
                                .description(categoryOptional.get().getDescription())
                                .name(categoryOptional.get().getName())
                                .build();
                        categories.add(categoryDto);
                    }
                }
                List<AuthorDto> authorDtos = new ArrayList<>();
                for (Integer author: bookRequest.getAuthorId()){
                    Optional<Author> authorOptional = authorRepository.findById(author);
                    if (authorOptional.isPresent()){
                        AuthorDto authorDto = AuthorDto.builder()
                                .id(authorOptional.get().getId())
                                .name(authorOptional.get().getName())
                                .description(authorOptional.get().getDescription())
                                .image(authorOptional.get().getImage())
                                .build();
                        authorDtos.add(authorDto);
                    }
                }
                BookResponse bookResponse = BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .isbn(book.getIsbn())
                        .coverImg(book.getCoverImg())
                        .publisher(book.getPublisher())
                        .publishDate(book.getPublishDate())
                        .author(authorDtos)
                        .categories(categories)
                        .qty(book.getQty())
                        .build();
                res.setStatus(true);
                res.setMessage("update success!" + " id" +id);
                res.setData(bookResponse);
               return ResponseEntity.ok(res);
            }else {
                res.setStatus(false);
                res.setMessage("update false!" +" id" + id);
                res.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> DeleteById(Integer id) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent() && !bookOptional.get().isDelete()){
                Book book = Book.builder()
                        .id(id)
                        .isbn(bookOptional.get().getIsbn())
                        .title(bookOptional.get().getTitle())
                        .description(bookOptional.get().getDescription())
                        .coverImg(bookOptional.get().getCoverImg())
                        .publisher(bookOptional.get().getPublisher())
                        .publishDate(bookOptional.get().getPublishDate())
                        .delete(true)
                        .build();
                bookRepository.save(book);
                res.setStatus(true);
                res.setMessage("delete success!" + " id" +id);
                res.setData(book);
                return ResponseEntity.ok(res);
            }else {
                res.setStatus(false);
                res.setMessage("delete false!" +" id" + id);
                res.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> addProductById(Integer id, ProductRequest productRequest) {

        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent() && !bookOptional.get().isDelete()){
                Book book = Book.builder()
                        .id(bookOptional.get().getId())
                        .isbn(bookOptional.get().getIsbn())
                        .title(bookOptional.get().getTitle())
                        .description(bookOptional.get().getDescription())
                        .coverImg(bookOptional.get().getCoverImg())
                        .publisher(bookOptional.get().getPublisher())
                        .publishDate(bookOptional.get().getPublishDate())
                        .price(bookOptional.get().getPrice())
                        .qty(bookOptional.get().getQty() + productRequest.getQty())
                        .delete(false)
                        .build();
                bookRepository.save(book);
                Vendor vendor = Vendor.builder()
                        .price(productRequest.getPrice())
                        .name(productRequest.getVendor())
                        .qty(productRequest.getQty())
                        .build();
                vendorRepository.save(vendor);
                res.setStatus(true);
                res.setMessage("add success!");
                res.setData(vendor);
                return ResponseEntity.ok(res);
            }else {
                res.setStatus(false);
                res.setMessage("add false!" );
                res.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> outProductById(Integer id, ProductRequest productRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser;
        if (authentication.getPrincipal() instanceof User) {
             currentUser = (User) authentication.getPrincipal();
            System.out.println("currentUser: " + currentUser);
            // rest of the code...
        } else {
            // Handle the case when the principal is not an instance of User
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent() && !bookOptional.get().isDelete()){
                Book book = Book.builder()
                        .id(bookOptional.get().getId())
                        .isbn(bookOptional.get().getIsbn())
                        .title(bookOptional.get().getTitle())
                        .description(bookOptional.get().getDescription())
                        .coverImg(bookOptional.get().getCoverImg())
                        .publisher(bookOptional.get().getPublisher())
                        .publishDate(bookOptional.get().getPublishDate())
                        .qty(bookOptional.get().getQty() - productRequest.getQty())
                        .price(bookOptional.get().getPrice())
                        .delete(false)
                        .build();
                bookRepository.save(book);
                Optional<Book> product = bookRepository.findById(id);
                CustomerProduct customerProduct = CustomerProduct.builder()
                        .customer_id(currentUser.getId())
                        .customer_name(currentUser.getFirstName() +" " + currentUser.getLastName())
                        .product_id(id)
                        .product_name(product.get().getTitle())
                        .qty(productRequest.getQty())
                        .price(book.getPrice())
                        .build();
                customerRepository.save(customerProduct);
                res.setStatus(true);
                res.setMessage("out success!");
                res.setData(customerProduct);
                return ResponseEntity.ok(res);
            }else {
                res.setStatus(false);
                res.setMessage("add false!" );
                res.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addBookOfTheWeek(Integer id) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent() && !bookOptional.get().isDelete()){
                Book book = Book.builder()
                        .id(id)
                        .isbn(bookOptional.get().getIsbn())
                        .title(bookOptional.get().getTitle())
                        .description(bookOptional.get().getDescription())
                        .coverImg(bookOptional.get().getCoverImg())
                        .publisher(bookOptional.get().getPublisher())
                        .qty(bookOptional.get().getQty())
                        .price(bookOptional.get().getPrice())
                        .publishDate(bookOptional.get().getPublishDate())
                        .ofTheWeek(true)
                        .build();
                bookRepository.save(book);
                res.setStatus(true);
                res.setMessage("delete success!" + " id" + id);
                res.setData(book);
                return ResponseEntity.ok(res);
            }else {
                res.setStatus(false);
                res.setMessage("delete false!" +" id" + id);
                res.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
//get book of the week
    @Override
    public ResponseEntity<?> getBookOfTheWeek() {
        try {
            List<BookResponse> bookObj = new ArrayList<>();
            List<Book> bookList = bookRepository.findAll();
            for (Book book: bookList){
                Optional<Book> bookOptional = bookRepository.findById(book.getId());
                if (bookOptional.isPresent() && !bookOptional.get().isDelete() && bookOptional.get().isOfTheWeek()){
                    List<CategoryDto> categoryObj = new ArrayList<>();
                    List<BookCategory> categoryByBook = bookCategoryRepository.findBookCategoryByBook(bookOptional.get());
                    for (BookCategory bookCategory: categoryByBook){

                        Optional<Category> categoryOptional = categoryRepository.findById(bookCategory.getCategory().getId());
                        if (categoryOptional.isPresent()){
                            CategoryDto categoryDto = CategoryDto.builder()
                                    .id(categoryOptional.get().getId())
                                    .name(categoryOptional.get().getName())
                                    .description(categoryOptional.get().getDescription())
                                    .build();
                            categoryObj.add(categoryDto);
                        }

                    }
                    List<AuthorDto> authorObj = new ArrayList<>();
                    List<AuthorBook> categoryByAuthor = authorBookRepository.findAllByBook(bookOptional.get());
                    for (AuthorBook authorBook :categoryByAuthor){
                        Optional<Author> authorOptional = authorRepository.findById(authorBook.getAuthors().getId());
                        if (authorOptional.isPresent()){
                            AuthorDto authorDto = AuthorDto.builder()
                                    .id(authorOptional.get().getId())
                                    .name(authorOptional.get().getName())
                                    .description(authorOptional.get().getDescription())
                                    .image(authorOptional.get().getImage())
                                    .build();
                            authorObj.add(authorDto);
                        }

                    }
                    BookResponse bookResponse = BookResponse.builder()
                            .id(bookOptional.get().getId())
                            .title(bookOptional.get().getTitle())
                            .description(bookOptional.get().getDescription())
                            .isbn(bookOptional.get().getIsbn())
                            .coverImg(bookOptional.get().getCoverImg())
                            .publisher(bookOptional.get().getPublisher())
                            .publishDate(bookOptional.get().getPublishDate())
                            .price(bookOptional.get().getPrice())
                            .qty(bookOptional.get().getQty())
                            .author(authorObj)
                            .categories(categoryObj)
                            .build();
                    bookObj.add(bookResponse);

                }
            }
            if (!bookObj.isEmpty()) {
                res.setStatus(true);
                res.setMessage("Fetch books successful!");
                res.setData(bookObj);
                return ResponseEntity.ok(res);
            } else {
                res.setStatus(false);
                res.setMessage("No books found!");
                res.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
