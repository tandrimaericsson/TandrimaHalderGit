package com.example.product_service.controller;


import com.example.product_service.entity.Product;
import com.example.product_service.model.ProductRequest;
import com.example.product_service.model.ProductResponse;
import com.example.product_service.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProductList")
    public ResponseEntity<List<Product>> getProductList(){
        return new ResponseEntity<>(productService.fetchAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<ProductRequest> productRequests) {
        return new ResponseEntity<>(productService.addProducts(productRequests), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId){
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId,@RequestParam("qty") Long qty){
        productService.reduceQuantity(productId,qty);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
