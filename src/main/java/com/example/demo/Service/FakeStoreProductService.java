package com.example.demo.Service;

import com.example.demo.Config.RedisTemplateConfig;
import com.example.demo.DTO.CreateProductDTO;
import com.example.demo.DTO.FakeStoreProductDTO;
import com.example.demo.DTO.PatchProductDTO;
import com.example.demo.DTO.UpdateProductDTO;
import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("fakestoreproductservice")
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;
    private final RedisTemplate redisTemplate;
    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }



    @Override
    public Product getProduct(Long id) {
        System.out.println("Debugging 1");
        //Is is to going to fetch product from fakestore?
        /*
        Check for the product with this id in the cache??
        if present, return, else go to db and fetch
         */

        Product cachedProduct = (Product) redisTemplate.opsForHash().get("Products", "Products_" + id); //Products, Products_1
        if(cachedProduct != null){
            /*
            Cache hit
             */
            return cachedProduct;
        }

        // Cache miss
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);

        System.out.println("Debugging");

        Product response = fakeStoreProductDTOResponse.getBody().toProduct();

        redisTemplate.opsForHash().put("Products", "Products_" + id, response);

        return response;
    }

    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDTO[] listOfProduct = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDTO dto : listOfProduct){
            productList.add(dto.toProduct());
        }
        return productList;
    }

    @Override
    public List<Product> getProductByLimit(Long id){
        FakeStoreProductDTO[] listOfProduct = restTemplate.getForObject("https://fakestoreapi.com/products?limit="+id, FakeStoreProductDTO[].class);
        return Arrays.stream(listOfProduct)
                .map(FakeStoreProductDTO::toProduct)
                .collect(Collectors.toList());
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

    @Override
    public void deleteProductFromDatabase(Long id) {

    }

    //Limit Product

    //Getting all Category
    @Override
    public List<String> getAllCategory() {
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
        return Arrays.asList(response);
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product createProductToDatabase(CreateProductDTO createProductDTO) {
        return null;
    }

    @Override
    public Product updateProductInDB(Long id, UpdateProductDTO updateProductDTO) {
        return null;
    }


}
