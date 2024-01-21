package com.marcosdanight.desafioanotaai.repository;

import com.marcosdanight.desafioanotaai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
