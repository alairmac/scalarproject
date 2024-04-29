package dev.alair.fresh.services;

import dev.alair.fresh.dtos.FakeStoreProductDto;
import dev.alair.fresh.models.Category;
import dev.alair.fresh.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);
    public List<Product> getAllProduct();
    public List<Category> getAllCategories();
    public Product updateProduct(Long id, String title, String description, String image,String category,double price);
    public Product deleteProduct(Long id);
    public List<Product> getCategoryProduct(String category);
    public Product createProduct(String title, String description, String image,String category,double price);
    public Category createCategory(String category);
}
