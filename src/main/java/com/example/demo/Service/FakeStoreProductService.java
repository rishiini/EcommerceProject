package com.example.demo.Service;

import com.example.demo.DTO.FakeStoreProductDTO;
import com.example.demo.DTO.PatchProductDTO;
import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



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
    public Product modifyProduct(Product p) {
//        FakeStoreProductDTO getProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + p.getId(), FakeStoreProductDTO.class);
//
//
////        if(getProduct != null){
//            getProduct.setTitle(p.getTitle());
//            getProduct.setPrice(p.getPrice());
//            getProduct.setCategory(p.getCategory().getTitle());
//            getProduct.setImageURL(p.getImageURL());
////        }
//        restTemplate.put("https://fakestoreapi.com/products/" + p.getId(), getProduct);
//        return getProduct.toProduct();
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        try {
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
            FakeStoreProductDTO deletedProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
            return deletedProduct.toProduct();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//    @Override
//    public Product updateProduct(Long id, Product p) {
//
//        FakeStoreProductDTO getProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
//        if(getProduct != null){
//            if (p.getTitle() != null) getProduct.setTitle(p.getTitle());
//            if (p.getPrice() != null) getProduct.setPrice(p.getPrice());
//            if(p.getDescription() != null) getProduct.setDescription(p.getDescription());
//            FakeStoreProductDTO patchProduct = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, p, FakeStoreProductDTO.class);
//            return patchProduct.toProduct();
//        }else {
//            throw new RuntimeException("Product not found with id: " + id);
//        }
//
//    }

    @Override
    public Product updateProduct(Long id, Product product) {
        //PATCH
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO fakeStoreProductDto = restTemplate.execute(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH,
                requestCallback,
                responseExtractor
        );

        return fakeStoreProductDto.toProduct();
    }
}
