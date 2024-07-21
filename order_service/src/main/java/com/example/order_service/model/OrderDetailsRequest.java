package com.example.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsRequest {
    private Long productId;
    private Long productQuantity;
    private long amount;
    private PaymentMode paymentMode;
}
