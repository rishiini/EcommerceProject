package com.example.demo.Service;

import com.example.demo.Model.Product;


public interface ProductService {
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product modifyProduct(Long id);
}
