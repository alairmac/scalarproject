package dev.alair.fresh.repositories;

import dev.alair.fresh.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);
    Product findByIdIs(long id);
    List<Product> findAll();
    List<Product> findByCategory_Title(String title);
}
