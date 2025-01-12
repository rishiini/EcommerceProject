package com.example.demo.Service;

import com.example.demo.Model.Category;
import com.example.demo.Model.Product;

import java.util.List;


public interface ProductService {
    Product getProduct(Long id);
    List<Product> getAllProduct();
    Product createProduct(Product product);
    Product modifyProduct(Product product);
    Product deleteProduct(Long id);
    Product updateProduct(Long id, Product product);

    List<Product> getProductByLimit(Long id);

    List<String> getAllCategory();

}
