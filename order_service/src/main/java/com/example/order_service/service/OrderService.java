package com.example.order_service.service;

import com.example.order_service.entity.OrderDetails;
import com.example.order_service.model.OrderDetailsRequest;

public interface OrderService {
    public OrderDetails createOrder(OrderDetailsRequest orderDetailsRequest);
}
