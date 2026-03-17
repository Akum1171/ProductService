package com.scaler.ProductService.services;

import com.scaler.ProductService.exception.ProductNotFoundException;
import com.scaler.ProductService.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

// it make more loosly coupled >> we should always implement services via interface
public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

   Page<Product> getAllProducts(int pageNumber, int pageSize);

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;

    //Page<Product> getAllProducts(int pageNumber, int pageSize);

    Product createProduct(Product product);

    void deleteProduct(Long id);

    void deleteProduct();
}