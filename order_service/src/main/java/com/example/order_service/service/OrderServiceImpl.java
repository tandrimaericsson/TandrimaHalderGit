package com.example.order_service.service;

import com.example.order_service.entity.OrderDetails;
import com.example.order_service.external.client.ProductService;
import com.example.order_service.model.OrderDetailsRequest;
import com.example.order_service.repository.OrderServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ProductService productService;

    @Override
    public OrderDetails createOrder(OrderDetailsRequest orderDetailsRequest) {
        log.info("Place order {}",Instant.now());
        OrderDetails orderDetails=new OrderDetails();
        copyProperties(orderDetailsRequest,orderDetails);
        orderDetails.setOrderDate(Instant.now());
        orderDetails.setOrderStatus("CREATED");

        productService.reduceQuantity(orderDetails.getProductId(),orderDetails.getProductQuantity());
        log.info("Quantity reduced by {} for product {}",orderDetails.getProductQuantity(),orderDetails.getProductId());
        orderServiceRepository.save(orderDetails);
        log.info("Order is placed with order id {}",orderDetails.getOrderId());
        return orderDetails;
    }
}
