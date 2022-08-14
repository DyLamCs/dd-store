package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Product;



public interface IProductService {
	List<Product> findAll();
	Optional <Product> findById(Integer id);
	Product create(Product product);
	Product update(Product productDetails, Integer id);
	Product delete(Integer id);
}