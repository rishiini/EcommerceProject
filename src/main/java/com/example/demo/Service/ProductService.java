package com.example.demo.Service;

import com.example.demo.DTO.CreateProductDTO;
import com.example.demo.DTO.UpdateProductDTO;
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
    void deleteProductFromDatabase(Long id);
    List<Product> getProductByLimit(Long limit);

    List<String> getAllCategory();
    Product getProductById(Long id);
    Product createProductToDatabase(CreateProductDTO createProductDTO);
    Product updateProductInDB(Long id, UpdateProductDTO updateProductDTO);
}
