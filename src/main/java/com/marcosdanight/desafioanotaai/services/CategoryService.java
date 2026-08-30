package com.marcosdanight.desafioanotaai.services;

import com.marcosdanight.desafioanotaai.domain.category.Category;
import com.marcosdanight.desafioanotaai.domain.category.CategoryDTO;
import com.marcosdanight.desafioanotaai.domain.category.execptions.CategoryNotFoundExecption;
import com.marcosdanight.desafioanotaai.repository.CategoryRepository;
import com.marcosdanight.desafioanotaai.services.aws.AwsSNSService;
import com.marcosdanight.desafioanotaai.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    private final AwsSNSService snsService;

    public CategoryService(CategoryRepository repository, AwsSNSService snsService) {
        this.repository = repository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.repository.save(newCategory);
        this.snsService.publish(new MessageDTO(newCategory.toString()));
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
        this.snsService.publish(new MessageDTO(categoryData.toString()));

        return category;
    }

    public void delete(String id) {
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundExecption::new);

        this.repository.delete(category);
    }

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }
}
