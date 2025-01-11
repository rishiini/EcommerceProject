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

    @Override
    public Product createProduct(Product product) {
        Product p1 = new Product();
        p1.setImageURL(product.getImageURL());
        p1.setCategory(product.getCategory());
        p1.setPrice(product.getPrice());
        p1.setTitle(product.getTitle());
        return p1;
    }

    //We're using put
    @Override
    public Product modifyProduct(Long id) {
        Product getProduct = restTemplate.getForObject("https://fakestoreapi.com/products/{id}", Product.class, id);

        Product p = new Product();
        if(getProduct != null){
            getProduct.setTitle(p.getTitle());
            getProduct.setPrice(p.getPrice());
            getProduct.setCategory(p.getCategory());
            getProduct.setImageURL(p.getImageURL());
        }
        restTemplate.put("https://fakestoreapi.com/products/{id}", getProduct, id);
        return p;

    }
}
