package com.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.entities.Brand;

@Repository
public interface BrandRepositoty extends JpaRepository<Brand, Integer> {

}
