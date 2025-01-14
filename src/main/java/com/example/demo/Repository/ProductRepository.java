package com.example.demo.Repository;

import com.example.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Get Single Product
    Product getProductById(Long id);

    List<Product> findAll();

    void deleteById(Long id);






}
