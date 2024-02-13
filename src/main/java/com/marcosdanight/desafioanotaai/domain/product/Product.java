package com.marcosdanight.desafioanotaai.domain.product;

import com.marcosdanight.desafioanotaai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    private Integer price;

    private String category;

    public Product(ProductDTO productData) {
        this.title = productData.title();
        this.description = productData.description();
        this.ownerId = productData.ownerId();
        this.price = productData.price();
        this.category = productData.categoryId();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("categoryId", this.category);
        json.put("id", this.id);
        json.put("price", this.price);
        json.put("type", "Product");

        return json.toString();
    }
}
