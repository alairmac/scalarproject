package dev.alair.fresh.controllers;

import dev.alair.fresh.dtos.CreateProductRequestDto;
import dev.alair.fresh.dtos.ErrorDto;
import dev.alair.fresh.dtos.FakeStoreProductDto;
import dev.alair.fresh.models.Category;
import dev.alair.fresh.models.Product;
import dev.alair.fresh.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto){
    return  productService.createProduct(
            productRequestDto.getTitle(),
            productRequestDto.getDescription(),
            productRequestDto.getImage(),
            productRequestDto.getCategory(),
            productRequestDto.getPrice()
            );
    }
    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequestDto productRequestDto){
        return productService.updateProduct(
                id,
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }
    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return productService.getAllCategories();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductById(@PathVariable("category") String category){
        return productService.getCategoryProduct(category);
    }
    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }


}
