package dev.alair.fresh.services;

import dev.alair.fresh.dtos.CreateProductRequestDto;
import dev.alair.fresh.dtos.FakeStoreCategoryDto;
import dev.alair.fresh.dtos.FakeStoreProductDto;
import dev.alair.fresh.models.Category;
import dev.alair.fresh.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .getForObject(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class
                );
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate
                .exchange(
                        "https://fakestoreapi.com/products",
                        HttpMethod.GET,
                        null,
                        FakeStoreProductDto[].class
                );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                products.add(fakeStoreProductDto.toProduct());
            }
            return products;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Category> getAllCategories() {
        // Make an HTTP GET request to fetch all categories from the Fake Store API
        ResponseEntity<FakeStoreCategoryDto[]> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/categories",
                HttpMethod.GET,
                null,
                FakeStoreCategoryDto[].class
        );

        // Check if the request was successful
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreCategoryDto[] fakeStoreCategoryDtos = responseEntity.getBody();
            List<Category> categories = new ArrayList<>();
            Long i = 0L;
            // Map each FakeStoreCategoryDto to a Category object
            for (FakeStoreCategoryDto fakeStoreCategoryDto : fakeStoreCategoryDtos) {
                Category category = new Category();
                category.setId(i++);
                category.setTitle(fakeStoreCategoryDto.getName());
                categories.add(category);
            }
            return categories;
        } else {
            // Handle the case where the request failed
            // You might want to throw an exception or return an empty list based on your requirements
            return Collections.emptyList();
        }
    }

    @Override
    public Product updateProduct(Long id, String title, String description, String image,String category,double price) {
        String url = "https://fakestoreapi.com/products/{id}";
        FakeStoreProductDto requestDto = new FakeStoreProductDto(title,description,image,category,price);

        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<FakeStoreProductDto>(requestDto);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody().toProduct();
        }else{
            return null;
        }
    }


    @Override
    public Product deleteProduct(Long id) {
        String url = "https://fakestoreapi.com/products/"+id;
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class
        );
        return responseEntity.getBody().toProduct();
    }

    @Override
    public List<Product> getCategoryProduct(String titlecategory) {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate
                .exchange(
                        "https://fakestoreapi.com/products/category/"+ titlecategory,
                        HttpMethod.GET,
                        null,
                        FakeStoreProductDto[].class
                );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                products.add(fakeStoreProductDto.toProduct());
            }
            return products;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        FakeStoreProductDto requestDto = new FakeStoreProductDto(title,description,image,category,price);

        FakeStoreProductDto responseDto = restTemplate.postForObject(
            "https://fakestoreapi.com/products",
                requestDto,
                FakeStoreProductDto.class
        );
        return responseDto.toProduct();
    }

    @Override
    public Category createCategory(String category) {
        return null;
    }
}
