package com.scaler.ProductService.controllers;

import com.scaler.ProductService.exception.ProductNotFound;
import com.scaler.ProductService.model.Product;
import com.scaler.ProductService.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFound {
        // path variable means read the varaible from path and set it inside this id parameter {/id}
        Product product= productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;

    }

    @GetMapping()
    public List<Product> getAllProduct() {
        List<Product> products=productService.getAllProducts();
        return products;
    }

    //create product
    /*public Product createProduct(){

    }*/
    // deleteProduct
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    //update product -> patch
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductPartially(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProductPartially(id, product);
        return ResponseEntity.ok(updatedProduct);
    }


    //replaceProduct -> put
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id,product);
    }
}
