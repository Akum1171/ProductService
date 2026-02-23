package com.scaler.ProductService.services;

import com.scaler.ProductService.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductServiceImpl implements ProductService{


    @Override
    public Product getProductById(Long id) {
        return new Product();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProductPartially(Long id, Product product) {
        return null;
    }
}
