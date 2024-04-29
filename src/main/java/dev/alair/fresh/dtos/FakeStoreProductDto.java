package dev.alair.fresh.dtos;

import dev.alair.fresh.models.Category;
import dev.alair.fresh.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;


    public FakeStoreProductDto(String title, String description, String image,String category,double price){
        setTitle(title);
        setDescription(description);
        setImage(image);
        setCategory(category);
        setPrice(price);
    }

    public Product toProduct(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImageUrl(getImage());
        product.setPrice(getPrice());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return product;
    }

}
