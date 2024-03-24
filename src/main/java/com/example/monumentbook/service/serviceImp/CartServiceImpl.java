package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.Cart;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.dto.CartDto;
import com.example.monumentbook.model.dto.UserDto;
import com.example.monumentbook.model.requests.CartRequest;
import com.example.monumentbook.model.requests.CartUpdateRequest;
import com.example.monumentbook.model.requests.RequestById;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        ResponseObject res = new ResponseObject();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();


            Optional<Book> book = bookRepository.findById(cartRequest.getBookId());

            if (book.isPresent()) {
                // Check if a cart entry already exists for the given userId and bookId
                Optional<Cart> existingCart = cartRepository.findByUserIdAndBookIdAndDeletedFalse(currentUser, book.get());
                if (existingCart.isPresent()) {
                    // Handle the conflict - cart entry already exists
                    res.setMessage("Cart entry already exists for the given user and book.");
                    res.setStatus(false);
                    res.setData(null);
                } else {
                    // Create and save the new cart entry
                    Cart cart = Cart.builder()
                            .userId(currentUser)
                            .bookId(book.get())
                            .qty(cartRequest.getQty())
                            .date(LocalDate.now())
                            .build();
                    cartRepository.save(cart);
                    // Build response objects
                    BookDto bookObj = buildBookDto(book.get());
                    UserDto userObj = buildUserDto(currentUser);
                    CartResponse cartResponse = CartResponse.builder()
                            .id(cart.getId())
                            .user(userObj)
                            .book(bookObj)
                            .qty(cart.getQty())
                            .date(cart.getDate())
                            .build();

                    res.setMessage("Add successful!");
                    res.setStatus(true);
                    res.setData(cartResponse);
                }
            } else {
                // Handle case where book or user is not found
                res.setMessage("Book or User not found.");
                res.setStatus(false);
                res.setData(null);
            }

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            // Handle any other exceptions
            res.setMessage("Error occurred while adding to cart.");
            res.setStatus(false);
            res.setData(null);
            return ResponseEntity.ok(res);
        }
    }
    @Override
    public ResponseEntity<?> getCart(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<Cart> pageResult = cartRepository.findByDeletedFalse(pageable);
            List<CartResponse> cartResponses = new ArrayList<>();
            for (Cart cart : pageResult.getContent()) {
                Optional<User> user = userRepository.findById((int)cart.getUserId().getId());
                UserDto userDto = user.map(this::buildUserDto).orElse(null);
                Optional<Book> book = bookRepository.findById(cart.getBookId().getId());
                BookDto bookDto = book.map(this::buildBookDto).orElse(null);
                CartResponse cartResponse = CartResponse.builder()
                        .id(cart.getId())
                        .user(userDto)
                        .book(bookDto)
                        .qty(cart.getQty())
                        .date(cart.getDate())
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
            Optional<Cart> cartOptional = cartRepository.findById(id);
            if (cartOptional.isPresent()){
                Optional<User> user = userRepository.findById((int)cartOptional.get().getUserId().getId());
                UserDto userDto= null;
                if (user.isPresent()){
                    userDto = buildUserDto(user.get());
                }
                Optional<Book> book = bookRepository.findById(cartOptional.get().getBookId().getId());
                BookDto bookDto= null;
                if (book.isPresent()){
                    bookDto =buildBookDto(book.get());
                }CartResponse cartResponse = CartResponse.builder()
                        .id(cartOptional.get().getId())
                        .user(userDto)
                        .book(bookDto)
                        .qty(cartOptional.get().getQty())
                        .date(cartOptional.get().getDate())
                        .build();
                res.setMessage("get successful!");
                res.setStatus(true);
                res.setData(cartResponse);
            }


            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage("get false!");
            res.setStatus(true);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> updateCartById(Integer id, CartUpdateRequest cartRequest) {
        ResponseObject res= new ResponseObject();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();

            Optional<Cart> cartOptional = cartRepository.findById(id);
            if (cartOptional.isPresent()){
                Optional<Book> book = bookRepository.findById(cartOptional.get().getBookId().getId());
                UserDto userDto= null;
                BookDto bookDto= null;
                if (book.isPresent()){
                    bookDto =buildBookDto(book.get());
                }
                Cart cart = Cart.builder()
                        .id(cartOptional.get().getId())
                        .userId(currentUser)
                        .bookId(book.get())
                        .qty(cartRequest.getQty())
                        .date(cartOptional.get().getDate())
                        .build();
                cartRepository.save(cart);
                CartResponse cartResponse = CartResponse.builder()
                        .id(cart.getId())
                        .user(userDto)
                        .book(bookDto)
                        .date(cart.getDate())
                        .qty(cart.getQty())
                        .build();
                res.setStatus(true);
                res.setData(cartResponse);
                res.setMessage("update successful!");
            }
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage("update false!");
            res.setStatus(false);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> deleteCartById(RequestById request) {
        ResponseObject res = new ResponseObject();
        try {
            List<Integer> ids = request.getIdList();
            List<Cart> cartsToDelete = cartRepository.findAllById(ids);
            if (!cartsToDelete.isEmpty()) {
                cartRepository.deleteAll(cartsToDelete);
                res.setMessage("Deleted carts with IDs: " + ids);
                res.setStatus(true);
                res.setData(null);
                return ResponseEntity.ok(res);
            } else {
                res.setMessage("Carts with provided IDs not found");
                res.setStatus(false);
                res.setData(null);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            res.setMessage("Failed to delete carts");
            res.setStatus(false);
            res.setData(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }



    @Override
    public ResponseEntity<?> findCartByUser(Integer page, Integer size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        try{
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<Cart> pageResult = cartRepository.findByUserIdIdAndDeletedFalse(currentUser.getId(), pageable);

            List<CartResponse> cartResponses = new ArrayList<>();
            for (Cart cart : pageResult.getContent()) {
                Optional<User> user = userRepository.findById((int)cart.getUserId().getId());
                UserDto userDto = user.map(this::buildUserDto).orElse(null);
                Optional<Book> book = bookRepository.findById(cart.getBookId().getId());
                BookDto bookDto = book.map(this::buildBookDto).orElse(null);
                CartResponse cartResponse = CartResponse.builder()
                        .id(cart.getId())
                        .user(userDto)
                        .qty(cart.getQty())
                        .date(cart.getDate())
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

    private BookDto buildBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .publisher(book.getPublisher())
                .description(book.getDescription())
                .title(book.getTitle())
                .coverImg(book.getCoverImg())
                .publishDate(book.getPublishDate())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .qty(book.getQty())
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
