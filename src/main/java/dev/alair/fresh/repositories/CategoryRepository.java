package dev.alair.fresh.repositories;

import dev.alair.fresh.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
    Category save(Category category);
    List<Category> findAll();

}
