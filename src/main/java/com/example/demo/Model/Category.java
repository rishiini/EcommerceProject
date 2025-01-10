package com.example.demo.Model;

import java.util.List;

//@Getter
//@Setter

public class Category {
    private long id;
    private String title;
//    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> listOfProduct;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getListOfProduct() {
        return listOfProduct;
    }

    public void setListOfProduct(List<Product> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }
}
