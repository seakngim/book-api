package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.Bookmarks;
import com.example.monumentbook.model.CustomerOrder;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.dto.BookDto;
import com.example.monumentbook.model.dto.UserDto;
import com.example.monumentbook.model.responses.ApiResponse;
import com.example.monumentbook.model.responses.OrderResponse;
import com.example.monumentbook.repository.BookRepository;
import com.example.monumentbook.repository.OrderRepository;
import com.example.monumentbook.repository.UserRepository;
import com.example.monumentbook.service.OrderService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<?> allCustomerOrder(Integer page, Integer size) {
//        ResponseObject res = new ResponseObject(); // Move inside the try block
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<CustomerOrder> pageResult = orderRepository.findAll(pageable);
            List<OrderResponse> orderList = new ArrayList<>();
            for(CustomerOrder customerOrder: pageResult){
                Optional<User> user = userRepository.findById((int)customerOrder.getUserId().getId());
                UserDto userDto = user.map(this::buildUserDto).orElse(null);
                Optional<Book> book = bookRepository.findById(customerOrder.getBookId().getId());
                BookDto bookDto = book.map(this::buildBookDto).orElse(null);
                Optional<CustomerOrder> customerOrderOptional = orderRepository.findById(customerOrder.getId());
                if (customerOrderOptional.isPresent()){
                    OrderResponse orderResponse = OrderResponse.builder()
                            .id(customerOrderOptional.get().getId())
                            .book(bookDto)
                            .user(userDto)
                            .price(customerOrderOptional.get().getPrice())
                            .qty(customerOrderOptional.get().getQty())
                            .date(customerOrderOptional.get().getDate())
                            .build();
                    orderList.add(orderResponse);
                }
                ApiResponse res = new ApiResponse(true, "Fetch books successful!", orderList, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
                return ResponseEntity.ok(res);
            }

            return ResponseEntity.ok(orderList);
        }catch (Exception e){
            ApiResponse res = new ApiResponse(true, "Fetch books successful!", null, 0, 0, 0,0);
            return ResponseEntity.ok(res);
        }
    }

    @Override
    public ResponseEntity<?> allCurrentOrder(Integer page, Integer size) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<CustomerOrder> pageResult = orderRepository.findByUserIdIdAndDeletedFalse(currentUser.getId(), pageable);
            List<OrderResponse> orderResponseList = new ArrayList<>();
            for (CustomerOrder customerOrder : pageResult) {
                Optional<User> user = userRepository.findById((int)customerOrder.getUserId().getId());
                UserDto userDto = user.map(this::buildUserDto).orElse(null);
                Optional<Book> book = bookRepository.findById(customerOrder.getBookId().getId());
                BookDto bookDto = book.map(this::buildBookDto).orElse(null);
                Optional<CustomerOrder> customerOrderOptional = orderRepository.findById(customerOrder.getId());
                if (customerOrderOptional.isPresent()) {
                    OrderResponse orderResponse = OrderResponse.builder()
                            .id(customerOrderOptional.get().getId())
                            .book(bookDto)
                            .user(userDto)
                            .price(customerOrderOptional.get().getPrice())
                            .qty(customerOrderOptional.get().getQty())
                            .date(customerOrderOptional.get().getDate())
                            .build();
                    orderResponseList.add(orderResponse);
                }
            }

            ApiResponse res = new ApiResponse(true, "Fetch books successful!", orderResponseList, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            ResponseObject res = new ResponseObject(); // Create a new object for error handling
            res.setMessage("fetch data failed");
            res.setStatus(false);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }private BookDto buildBookDto(Book book) {
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
