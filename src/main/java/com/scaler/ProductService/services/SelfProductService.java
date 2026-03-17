package com.scaler.ProductService.services;

import com.scaler.ProductService.exception.ProductNotFoundException;
import com.scaler.ProductService.model.Product;
import com.scaler.ProductService.repository.CategoryRepository;
import com.scaler.ProductService.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SelfProductService implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

   public SelfProductService(ProductRepository productRepository,
                       CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id, "Product not found"));
    }


    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("price").ascending()));
    }





    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {

        Product existingProduct = getProductById(id);

        if (product.getTitle() != null) {
            existingProduct.setTitle(product.getTitle());
        }

        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {

        Product existingProduct = getProductById(id);

        existingProduct.setTitle(product.getTitle());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }



    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        productRepository.deleteById(id);
    }

    @Override
    public void deleteProduct() {

    }
}