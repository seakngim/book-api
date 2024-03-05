package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.CustomerOrder;
import com.example.monumentbook.model.User;
import com.example.monumentbook.model.responses.OrderResponse;
import com.example.monumentbook.repository.OrderRepository;
import com.example.monumentbook.service.OrderService;
import com.example.monumentbook.utilities.response.ResponseObject;
import lombok.RequiredArgsConstructor;
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
    ResponseObject res = new ResponseObject();
    @Override
    public ResponseEntity<?> allCustomerOrder() {
        try {
            List<CustomerOrder> orders = orderRepository.findAll();
            List<OrderResponse> orderList = new ArrayList<>();
            for(CustomerOrder customerOrder: orders){
                Optional<CustomerOrder> optionalOrder = orderRepository.findById(customerOrder.getId());
                if (optionalOrder.isPresent()){
                    OrderResponse orderResponse = OrderResponse.builder()
                            .id(optionalOrder.get().getId())
                            .customer_id(optionalOrder.get().getCustomerId())
                            .customer_name(optionalOrder.get().getCustomerName())
                            .phoneNumber(optionalOrder.get().getPhoneNumber())
                            .product_id(optionalOrder.get().getProductId())
                            .price(optionalOrder.get().getPrice())
                            .qty(optionalOrder.get().getQty())
                            .date(optionalOrder.get().getDate())
                            .build();
                    orderList.add(orderResponse);
                }
                res.setStatus(true);
                res.setMessage("fetch data successful!");
                res.setData(orderList);
            }

            return ResponseEntity.ok(orderList);
        }catch (Exception e){
            res.setData(null);
            res.setStatus(false);
            res.setMessage("fetch data false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> allCurrentOrder() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            System.out.println("currentUser" + currentUser);

            List<CustomerOrder> orderList = orderRepository.findAllByCustomerId(currentUser.getId());
            List<OrderResponse> customerOrderList = new ArrayList<>();
            ResponseObject res = new ResponseObject(); // Move inside the try block

            for (CustomerOrder customerOrder : orderList) {
                Optional<CustomerOrder> customerOrderOptional = orderRepository.findById(customerOrder.getId());

                if (customerOrderOptional.isPresent()) {
                    OrderResponse orderResponse = OrderResponse.builder()
                            .id(customerOrderOptional.get().getId())
                            .customer_id(customerOrderOptional.get().getCustomerId())
                            .customer_name(customerOrderOptional.get().getCustomerName())
                            .phoneNumber(customerOrderOptional.get().getPhoneNumber())
                            .product_id(customerOrderOptional.get().getProductId())
                            .price(customerOrderOptional.get().getPrice())
                            .qty(customerOrderOptional.get().getQty())
                            .date(customerOrderOptional.get().getDate())
                            .build();
                    customerOrderList.add(orderResponse);
                }
            }
            if (customerOrderList.isEmpty()) {
                res.setMessage("No data found for the current user");
                res.setStatus(false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

            res.setMessage("fetch data successful!");
            res.setStatus(true);
            res.setData(customerOrderList);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            ResponseObject res = new ResponseObject(); // Create a new object for error handling
            res.setMessage("fetch data failed");
            res.setStatus(false);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
