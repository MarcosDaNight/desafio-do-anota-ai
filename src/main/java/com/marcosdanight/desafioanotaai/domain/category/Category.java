package com.marcosdanight.desafioanotaai.domain.category;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    public Category(CategoryDTO categoryData) {
        this.title = categoryData.title();
        this.description = categoryData.description();
        this.ownerId = categoryData.ownerId();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("id", this.id);
        json.put("type", "Category");

        return json.toString();
    }
}
