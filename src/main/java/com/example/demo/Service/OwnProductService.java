package com.example.demo.Service;

import com.example.demo.DTO.CreateProductDTO;
import com.example.demo.DTO.UpdateProductDTO;
import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import java.util.List;

@Service("ownproductservice")
//@RequiredArgsConstructor
public class OwnProductService implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public OwnProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProduct(Long id) {
       return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product modifyProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProductFromDatabase(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductByLimit(Long limit) {
        Pageable pageable = PageRequest.of(0, limit.intValue());
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public List<String> getAllCategory() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Product createProductToDatabase(CreateProductDTO createProductDTO) {
        Product p = new Product();
        p.setTitle(createProductDTO.getTitle());
        p.setDescription(createProductDTO.getDescription());
        p.setPrice(createProductDTO.getPrice());
        p.setImageURL(createProductDTO.getImageURL());

            Category categoryFromDatabase = categoryRepository.findByTitle(createProductDTO.getCategory());
        if(categoryFromDatabase == null){
            Category newCategory = new Category();
            newCategory.setTitle(createProductDTO.getCategory());
            categoryFromDatabase = newCategory;
        }
        p.setCategory(categoryFromDatabase);
        return productRepository.save(p);

    }

    @Override
    public Product updateProductInDB(Long id, UpdateProductDTO updateProductDTO) {
        Product productById = productRepository.getProductById(id);
        productById.setTitle(updateProductDTO.getTitle());
        productById.setPrice(updateProductDTO.getPrice());
        productById.setImageURL(updateProductDTO.getImageURL());

        Category categoryFromDatabase = categoryRepository.findByTitle(updateProductDTO.getCategory());
        if (categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(updateProductDTO.getCategory());
            categoryFromDatabase = newCategory;
        }
        productById.setCategory(categoryFromDatabase);
        productRepository.save(productById);
        return productById;
    }

}
