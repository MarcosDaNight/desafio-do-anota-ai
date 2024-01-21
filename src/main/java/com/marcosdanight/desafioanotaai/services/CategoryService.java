package com.marcosdanight.desafioanotaai.services;

import com.marcosdanight.desafioanotaai.domain.category.Category;
import com.marcosdanight.desafioanotaai.domain.category.CategoryDTO;
import com.marcosdanight.desafioanotaai.domain.category.execptions.CategoryNotFoundExecption;
import com.marcosdanight.desafioanotaai.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.repository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundExecption::new);

        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.repository.save(category);

        return category;
    }

    public void delete(String id) {
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundExecption::new);

        this.repository.delete(category);
    }
}
