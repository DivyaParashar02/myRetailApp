package com.poc.app.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.app.myRetail.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	public Product findProductByproductId(String productId);

}
