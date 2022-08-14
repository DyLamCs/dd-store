package com.web.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Brand;
import com.web.repositories.BrandRepositoty;
import com.web.services.IBrandService;
@Service
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	BrandRepositoty brandRepositoty;
	
	

	@Override
	public List<Brand> findAll() {
		return brandRepositoty.findAll();
	}

	@Override
	public Optional<Brand> findById(Integer id) {
		return brandRepositoty.findById(id);
	}

	@Override
	public Brand create(Brand brand) {
		return brandRepositoty.save(brand);
	}

	@Override
	public Brand update(Brand brandDetails, Integer id ) {
		Brand brand = brandRepositoty.findById(id).get();
		
		brand.setBraName(brandDetails.getBraName());
		
		return brandRepositoty.save(brand);
	}

	@Override
	public void delete(Integer id) {
		brandRepositoty.deleteById(id);
	}

}
