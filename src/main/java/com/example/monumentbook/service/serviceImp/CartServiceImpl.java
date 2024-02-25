package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.Cart;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.dto.CartDto;
import com.example.monumentbook.model.dto.UserDto;
import com.example.monumentbook.model.requests.CartRequest;
import com.example.monumentbook.model.responses.ApiResponse;
import com.example.monumentbook.model.responses.CartResponse;
import com.example.monumentbook.repository.BookRepository;
import com.example.monumentbook.repository.CartRepository;
import com.example.monumentbook.repository.UserRepository;
import com.example.monumentbook.service.CartService;
import com.example.monumentbook.utilities.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    @Override
    public ResponseEntity<?> addToCart(CartRequest cartRequest) {
        ResponseObject res= new ResponseObject();
        try {
            Optional<Book> book = bookRepository.findById(cartRequest.getBookId());
            Optional<User> user = userRepository.findById(cartRequest.getUserId());
            if (book.isPresent() && user.isPresent()){
                Cart cart = Cart.builder()
                        .userId(user.get())
                        .bookId(book.get())
                        .build();
                cartRepository.save(cart);
                BookDto bookObj = buildBookDto(book.get());
                UserDto userObj = buildUserDto(user.get());
               CartResponse cartResponse = CartResponse.builder()
                        .user(userObj)
                        .book(bookObj)
                        .build();

                res.setMessage("add successful!");
                res.setStatus(true);
                res.setData(cartResponse);
            }
            return  ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage("add successful!");
            res.setStatus(true);
            res.setData(null);
            return  ResponseEntity.ok(res);
        }

    }

    @Override
    public ResponseEntity<?> getCart(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<Cart> pageResult = cartRepository.findAll(pageable);

            List<CartResponse> cartResponses = new ArrayList<>();

            for (Cart cart : pageResult.getContent()) {
                Optional<User> user = userRepository.findById(cart.getId());
                UserDto userDto = user.map(this::buildUserDto).orElse(null);

                Optional<Book> book = bookRepository.findById(cart.getId());
                BookDto bookDto = book.map(this::buildBookDto).orElse(null);

                CartResponse cartResponse = CartResponse.builder()
                        .user(userDto)
                        .book(bookDto)
                        .build();
                cartResponses.add(cartResponse);
            }
            ApiResponse res = new ApiResponse(true, "Fetch books successful!", cartResponses, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            ApiResponse res = new ApiResponse(true, "Fetch books successful!", null, 0, 0, 0,0);
            return ResponseEntity.ok(res);
        }
    }


    @Override
    public ResponseEntity<?> getCartById(Integer id) {
        ResponseObject res= new ResponseObject();
        try {
                Optional<User> user = userRepository.findById(id);
                UserDto userDto= null;
                if (user.isPresent()){
                    userDto = buildUserDto(user.get());
                }
                Optional<Book> book = bookRepository.findById(id);
                BookDto bookDto= null;
                if (book.isPresent()){
                    bookDto =buildBookDto(book.get());
                }CartResponse cartResponse = CartResponse.builder()
                        .user(userDto)
                        .book(bookDto)
                        .build();
                res.setMessage("get successful!");
                res.setStatus(true);
                res.setData(cartResponse);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage("get false!");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> updateCartById(Integer id) {
        ResponseObject res= new ResponseObject();
        try {
            Optional<User> user = userRepository.findById(id);
            UserDto userDto= null;
            if (user.isPresent()){
                userDto = buildUserDto(user.get());
            }
            Optional<Book> book = bookRepository.findById(id);
            BookDto bookDto= null;
            if (book.isPresent()){
                bookDto =buildBookDto(book.get());
            }
            Cart cart = Cart.builder()
                    .id(id)
                    .userId(user.get())
                    .bookId(book.get())
                    .build();
            cartRepository.save(cart);
            CartResponse cartResponse = CartResponse.builder()
                    .user(userDto)
                    .book(bookDto)
                    .build();

            res.setMessage("update successful!");
            res.setStatus(true);
            res.setData(cartResponse);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage("update false!");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> DeleteCartById(Integer id) {
        ResponseObject res= new ResponseObject();
        try {
            Optional<User> user = userRepository.findById(id);
            UserDto userDto= null;
            if (user.isPresent()){
                userDto = buildUserDto(user.get());
            }
            Optional<Book> book = bookRepository.findById(id);
            BookDto bookDto= null;
            if (book.isPresent()){
                bookDto =buildBookDto(book.get());
            }
            Cart cart = Cart.builder()
                    .id(id)
                    .userId(user.get())
                    .bookId(book.get())
                    .delete(true)
                    .build();
            cartRepository.save(cart);
            CartResponse cartResponse = CartResponse.builder()
                    .user(userDto)
                    .book(bookDto)
                    .build();

            res.setMessage("add successful!");
            res.setStatus(true);
            res.setData(cartResponse);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage("add false!");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private BookDto buildBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .publisher(book.getPublisher())
                .description(book.getDescription())
                .title(book.getTitle())
                .coverImg(book.getCoverImg())
                .publishDate(book.getPublishDate())
                .isbn(book.getIsbn())
                .build();
    }

    private UserDto buildUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .coverImage(user.getCoverImg())
                .email(user.getEmail())
                .build();
    }

}
