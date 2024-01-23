package com.marcosdanight.desafioanotaai.services;

import com.marcosdanight.desafioanotaai.domain.category.Category;
import com.marcosdanight.desafioanotaai.domain.category.execptions.CategoryNotFoundExecption;
import com.marcosdanight.desafioanotaai.domain.product.Product;
import com.marcosdanight.desafioanotaai.domain.product.ProductDTO;
import com.marcosdanight.desafioanotaai.domain.product.execptions.ProductNotFoundExecption;
import com.marcosdanight.desafioanotaai.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CategoryService categoryService;
    private ProductRepository repository;

    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.repository = repository;
    }


    public Product insert(ProductDTO productData) {
        Category category = this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundExecption::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        repository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }


    public Product update(String id, ProductDTO productData) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundExecption::new);

        this.categoryService.getById(productData.categoryId())
                .ifPresent(product::setCategory);

        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (!productData.description().isEmpty()) product.setDescription(productData.description());
        if (!(productData.price() == null)) product.setPrice(product.getPrice());


        this.repository.save(product);

        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundExecption::new);

        this.repository.delete(product);
    }
}
