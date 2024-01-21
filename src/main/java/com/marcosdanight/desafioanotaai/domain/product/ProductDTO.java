package com.marcosdanight.desafioanotaai.domain.product;

import com.marcosdanight.desafioanotaai.domain.category.Category;

public record ProductDTO(String title, String description, String ownerId,Integer price, String categoryId) {
}
