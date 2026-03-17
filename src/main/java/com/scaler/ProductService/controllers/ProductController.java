package com.scaler.ProductService.controllers;

import com.scaler.ProductService.common.AuthCommons;
import com.scaler.ProductService.dtos.Role;
import com.scaler.ProductService.dtos.UserDto;
import com.scaler.ProductService.exception.ProductNotFoundException;
import com.scaler.ProductService.model.Product;
import com.scaler.ProductService.repository.ProductRepository;
import com.scaler.ProductService.services.ProductService;
import com.scaler.ProductService.services.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.List;


//localhost:8080/products
@RestController //This controller is going to REST HTTP API's
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthCommons authCommons;
    private final ProductRepository productRepository;
    private RestTemplate restTemplate;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService,
                             AuthCommons authCommons,
                             ProductRepository productRepository,
                             RestTemplate restTemplate) {
        this.productService = productService;
        this.authCommons = authCommons;
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    //localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //Call UserService ValidateToken API to validate the token.
//        UserDto userDto = authCommons.validateToken(token);
        ResponseEntity<Product> responseEntity;

        //Make a call to UserService
//        UserDto userDto =
//                restTemplate.getForObject("http://userserviceevebatch/users/10", UserDto.class);

//        if (userDto != null) {
//            return product;
//        }


//        if (userDto == null) {
//            responseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//            return responseEntity;
//        }

        //Role Based Access.
//        for (Role role : userDto.getRoles()) {
//            if (role.getValue().equals("ADMIN")) {
//                //provide access.
//            }
//        }
        System.out.println("Got the request in Product Service");
        Product product = productService.getProductById(id);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);

//        product.setTitle("Macbook Air");
////        product.setPrice(120000.0);
//        ResponseEntity<Product> responseEntity;
//        if (product == null) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return responseEntity;
//        }

//        Product sampleProduct = new Product();
        return responseEntity;

//        ResponseEntity<Product> responseEntity = null;
//        try {
//            Product product = productService.getProductById(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (ArithmeticException e) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        //Controllers should be as light as possible.
//        return responseEntity;
        //throw new RuntimeException("Something went wrong");
    }

    // localhost:8080/products
    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                        @RequestParam("pageSize") int pageSize) {
        //return List.of(new Product(), new Product(), new Product());
//        List<Product> actualProducts = productService.getAllProducts(); // 1234
//        Product product = new Product();
//        product.setId(4L);
//        product.setTitle("macbook pro");
//        actualProducts.add(product);
//        return actualProducts;

        return productService.getAllProducts(pageNumber, pageSize);
    }

    //createProduct
    //deleteProduct
    //updateProduct -> Partial Update (PATCH)
    //replaceProduct -> Replace (PUT)

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(id, product);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleSomeException() {
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) { // can use DTO as well.
        return productService.createProduct(product);
    }
}