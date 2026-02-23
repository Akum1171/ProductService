package com.scaler.ProductService.services;

import com.scaler.ProductService.exception.ProductNotFound;
import com.scaler.ProductService.model.Product;

import java.util.List;

// it make more loosly coupled >> we should always implement services via interface
public interface ProductService {

   Product getProductById(Long id) throws ProductNotFound;

   List<Product> getAllProducts();

   Product replaceProduct(Long id, Product product);

    Product deleteProduct(Long id);

   Product updateProductPartially(Long id, Product product);
}
