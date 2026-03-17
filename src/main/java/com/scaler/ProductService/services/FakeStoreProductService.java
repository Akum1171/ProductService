package com.scaler.ProductService.services;

import com.scaler.ProductService.dtos.FakeStoreProductDto;
import com.scaler.ProductService.exception.ProductNotFoundException;
import com.scaler.ProductService.model.Category;
import com.scaler.ProductService.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    FakeStoreProductService(RestTemplate restTemplate,
                            RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException{
        //Call FakeStore API here to get the Product with given id.

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" + id);

        if (product != null) {
            //Cache HIT
            return product;
        }

        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);
        //1st param -> URL
        //2nd param -> Response

        product = convertFakeStoreDtoToProduct(fakeStoreProductDto);

//        if (fakeStoreProductDto == null) {
//            throw new ProductNotFoundException(id, "Product with id " + id + " not found");
//            //return null;
//        }

        //Store the data inside the Redis.
        /*
        Map Name : PRODUCTS
        Id : Key
        Value : Product object
         */
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, product);

        //Convert FakeStore DTO into Product object.
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
//        FakeStoreProductDto[] fakeStoreProductDtos =
//                restTemplate.getForObject("https://fakestoreapi.com/products",
//                        FakeStoreProductDto[].class);
//
//        //convert List of FakeStoreProductDtos to List of Products
//        List<Product> response = new ArrayList<>();
//        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
//            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
//        }
//
//        return response;
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDto response =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreDtoToProduct(response);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public void deleteProduct() {

    }
}