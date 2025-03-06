package com.example.demo.Controller;

import com.example.demo.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @Mock
    @Qualifier("fakestoreproductservice")
    private ProductService productService;

    @Test
    void getProduct() {
    }

    @Test
    void getAllProduct() {
    }

    @Test
    void getProductByLimit() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void modifyProduct() {
    }

    @Test
    void getAllCategory() {
    }
}