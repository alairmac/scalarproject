package dev.alair.fresh.services;

import dev.alair.fresh.models.Category;
import dev.alair.fresh.models.Product;
import dev.alair.fresh.repositories.CategoryRepository;
import dev.alair.fresh.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return productRepository.findByIdIs(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, String title, String description, String image, String category, double price) {
        Product product = productRepository.findByIdIs(id);
        if (product != null) {
            product.setTitle(title);
            product.setDescription(description);
            product.setImageUrl(image);
            product.setPrice(price);
            Category categoryFromDB = createCategory(category);
            product.setCategory(categoryFromDB);
        }
        return productRepository.save(product);

    }

    @Override
    public Boolean deleteProduct(Long id) {
        Product product = productRepository.findByIdIs(id);
        if (product != null) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getCategoryProduct(String category) {
        return productRepository.findByCategory_Title(category);
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        Category categoryFromDB = createCategory(category);
        product.setCategory(categoryFromDB);
        return productRepository.save(product);
    }

    @Override
    public Category createCategory(String category) {
        Category categoryFromDB = categoryRepository.findByTitle(category);
        if (categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDB = categoryRepository.save(newCategory);
        }
        return categoryFromDB;
    }
}
