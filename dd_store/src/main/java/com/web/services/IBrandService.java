package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Brand;

public interface IBrandService {
	List<Brand> findAll();
	Optional <Brand> findById(Integer id);
	Brand create(Brand brand);
	Brand update(Brand brandDetails, Integer id );
	void delete(Integer id);
}
