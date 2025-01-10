package com.example.demo.Service;

import com.example.demo.DTO.FakeStoreProductDTO;
import com.example.demo.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class FakeStoreProductService implements ProductService {
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Product getProduct(Long id) {
        try {
            ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
            return Objects.requireNonNull(response.getBody()).toProduct();
        } catch (HttpServerErrorException e) {
            // Handle the server error (e.g., log it, return a default value, etc.)
            System.err.println("Server error: " + e.getStatusCode() + " - " + e.getStatusText());
            // Return a default Product or handle it as per your application's requirement
            return new Product(); // Placeholder for a default product
        }
    }
}
