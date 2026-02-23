package com.scaler.ProductService.services;

import com.scaler.ProductService.dtos.FakeStoreProductDto;
import com.scaler.ProductService.exception.ProductNotFound;
import com.scaler.ProductService.model.Category;
import com.scaler.ProductService.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreServiceImpl implements ProductService{


   private RestTemplate restTemplate;

   public FakeStoreServiceImpl(RestTemplate restTemplate){
       this.restTemplate=restTemplate;
   }

   private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
       Product product =new Product();
       product.setId(dto.getId());
       product.setTitle(dto.getTitle());
       product.setPrice(dto.getPrice());
       product.setDescription(dto.getDescription());
       product.setImage(dto.getImage());

       Category category=new Category();
       category.setDescription(dto.getDescription());
       product.setCategory(category);

       return product;


   }

    @Override
    public Product getProductById(Long id) throws ProductNotFound {
       //int x=1/0;
        // Call fake store api/ 3rd party here to get the product with given id
        // why we use dto bcs in fake store we getting as reponse as category type as a string but in entity we have class, so we use dto to exact match
       FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(fakeStoreProductDto==null){
            throw new ProductNotFound("Product with id " + id + "not found");
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);

        // as we can see getAllProducts is returning List<Product>, so we have to convert List<FakeStoreProductDto> to List<Product>
        List<Product> response = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDtoCon : fakeStoreProductDto) {
            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDtoCon));

        }
        return response;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreDtoToProduct(response);

    }
    @Override
    public Product updateProductPartially(Long id, Product product) {

        FakeStoreProductDto requestDto = new FakeStoreProductDto();

        if (product.getTitle() != null) {
            requestDto.setTitle(product.getTitle());
        }
        if (product.getDescription() != null) {
            requestDto.setDescription(product.getDescription());
        }
        if (product.getImage() != null) {
            requestDto.setImage(product.getImage());
        }

        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(
                        "https://fakestoreapi.com/products/" + id,
                        HttpMethod.PATCH,
                        requestEntity,
                        FakeStoreProductDto.class
                );

        return convertFakeStoreDtoToProduct(response.getBody());
    }

    @Override
    public Product deleteProduct(Long id) {

        FakeStoreProductDto response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class
        ).getBody();

        return convertFakeStoreDtoToProduct(response);
    }

}
