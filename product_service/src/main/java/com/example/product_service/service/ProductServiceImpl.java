package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.exception.ProductServiceCustomException;
import com.example.product_service.model.ProductRequest;
import com.example.product_service.model.ProductResponse;
import com.example.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> addProducts(List<ProductRequest> productRequests) {
        log.info("Adding products");
        List<Product> productList = productRequests.stream()
                .map(productRequest -> Product.builder()
                        .name(productRequest.getName())
                        .price(productRequest.getPrice())
                        .quantity(productRequest.getQuantity())
                        .build()).toList();
        productRepository.saveAll(productList);
        log.info("Products added successfully");
        return productList;
    }

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product= productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product not found with product id "+productId,
                        "NOT_FOUND", HttpStatus.NOT_FOUND));
        ProductResponse productResponse=new ProductResponse();
        copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(Long productId,Long quantity) {
        log.info("Reduce quantity by {} for product id : ",quantity,productId);
        Product product= productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product not found with product id "+productId,
                        "NOT_FOUND", HttpStatus.NOT_FOUND));
        if(quantity>product.getQuantity())
            throw new ProductServiceCustomException("Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY",HttpStatus.NOT_ACCEPTABLE);
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product quantity reduced.");
    }


}
