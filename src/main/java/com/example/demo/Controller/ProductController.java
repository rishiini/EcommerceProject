package com.example.demo.Controller;

import com.example.demo.DTO.CreateProductDTO;
import com.example.demo.DTO.UpdateProductDTO;
import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.OwnProductService;
import com.example.demo.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final OwnProductService ownProductService;
    private final ProductRepository productRepository;

    public ProductController(@Qualifier("fakestoreproductservice") ProductService productService, OwnProductService ownProductService, ProductRepository productRepository) {
        this.productService = productService;
        this.ownProductService = ownProductService;
        this.productRepository = productRepository;
    }

    @GetMapping({"/{id}"})
    public Product getProduct(@PathVariable("id") Long id){
//        return productService.getPropduct(id);
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
    public Product createProduct(@RequestBody CreateProductDTO createProductDTO){
        return ownProductService.createProductToDatabase(createProductDTO);
    }

    @PutMapping("")
    public Product modifyProduct(@RequestBody Product product){
        return productService.modifyProduct(product);
    }

//    @PatchMapping("/{id}")
//    public Product updateUserStatus(@PathVariable Long id, @RequestBody UpdateProductDTO product){
//        return ownProductService.updateProductInDB(id, product);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteProduct(@PathVariable Long id){
//        productService.deleteProductFromDatabase(id);
//        return "Successfully Deleted";
//    }

    //Category
    //Fetching all the categories
    @GetMapping("/categories")
    public List<String> getAllCategory(){
        return productService.getAllCategory();
    }
}
