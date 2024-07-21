package com.example.order_service.controller;

import com.example.order_service.entity.OrderDetails;
import com.example.order_service.model.OrderDetailsRequest;
import com.example.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDetails> createOrder(@RequestBody OrderDetailsRequest orderDetailsRequest){
        return new ResponseEntity<>(orderService.createOrder(orderDetailsRequest), HttpStatus.CREATED);
    }


}
