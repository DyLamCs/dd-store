package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Category;

public interface ICategoriesService {
	List<Category> findAll();
	Optional<Category> findById(Integer id);
	Category create(Category category);
	Category update(Category categoryDetails, Integer id);
	void delete(Integer id);
}
