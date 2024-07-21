package com.example.product_service.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductRequest {
    private String name;

    private Long price;

    private Long quantity;
}
