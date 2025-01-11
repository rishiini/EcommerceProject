package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product modifyProduct(@PathVariable("id") Long id){
        return productService.modifyProduct(id);
    }

}
