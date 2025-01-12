package com.example.demo.Controller;

import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products/")
//@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/{id}"})
    public Product getProduct(@PathVariable("id") Long id){
        return productService.getProduct(id);
    }

    @GetMapping("")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/limit")
    @ResponseBody
    public List<Product> getProductByLimit(@RequestParam("limit") Long limit){
        return productService.getProductByLimit(limit);
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("")
    public Product modifyProduct(@RequestBody Product product){
        return productService.modifyProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateUserStatus(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }


    //Category
    //Fetching all the categories
    @GetMapping("/categories")
    public List<String> getAllCategory(){
        return productService.getAllCategory();
    }
}
