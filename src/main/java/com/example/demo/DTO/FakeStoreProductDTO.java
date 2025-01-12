package com.example.demo.DTO;

import com.example.demo.Model.Category;
import com.example.demo.Model.Product;

import java.util.List;


public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private String price;
    private String category;
    private String imageURL;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setTitle(title);
        product.setPrice(price);
        product.setImageURL(imageURL);

        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);

        return product;
    }


    public String getTitle() {
        return title;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
