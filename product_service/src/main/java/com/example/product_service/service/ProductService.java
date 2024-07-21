package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.model.ProductRequest;
import com.example.product_service.model.ProductResponse;

import java.util.List;

public interface ProductService {

    public List<Product> addProducts(List<ProductRequest> productRequests);

    public List<Product> fetchAllProducts();

    public ProductResponse getProductById(Long productId);
    public void reduceQuantity(Long productId,Long quantity);
}
