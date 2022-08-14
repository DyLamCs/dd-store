package com.web.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Brand;
import com.web.entities.Category;
import com.web.entities.Product;
import com.web.repositories.ProductRepository;
import com.web.services.IBrandService;
import com.web.services.ICategoriesService;
import com.web.services.IProductService;
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	ProductRepository productRepository;
	
	
	@Autowired
	IBrandService iBrandService;
	@Autowired
	ICategoriesService iCategoriesService;
	

	/**
	 * Tim tất cả các đối tượng có trong DB
	 */
	@Override
	public List<Product> findAll() {
		return productRepository.findAllByproDeleted((byte) 0);
	}

	

	/**
	 * Tạo một đối tượng mới vào DB
	 */
	@Override
	public Product create(Product product) {
		product.setProDatecrate(new Date());
		product.setProDatemodify(new Date());
		return productRepository.save(product);
	}

	/**
	 * Chỉnh sửa một đối tượng có trong DB
	 */
	@Override
	public Product update(Product productDetails, Integer id) {
		Product product = productRepository.findById(id).get();   
        Optional<Brand> brand = iBrandService.findById(productDetails.getBrand().getBraId());
        Optional<Category> category = iCategoriesService.findById(productDetails.getCategory().getCtgId());

        product.setProName(productDetails.getProName());
        product.setProImage(productDetails.getProImage());
        product.setBrand(brand.get());
        product.setCategory(category.get());
        product.setProPrice(productDetails.getProPrice());
        product.setProDetail(productDetails.getProDetail());
        product.setProCode(productDetails.getProCode());
        product.setProQuantity(productDetails.getProQuantity());
        product.setProDatemodify(new Date());
        
        return productRepository.save(product);
	}

	/**	
	 * Xóa một đối tượng ra khỏi DB
	 */
	
	@Override
	public Product delete(Integer id) {
		Product product = productRepository.findById(id).get();
		product.setProDeleted((byte)1);
		return productRepository.save(product);
	}

	/**
	 * Tìm một đốit tượng thông qua Id có trong DB
	 */
	@Override
	public Optional<Product> findById(Integer id) {
		
		return productRepository.findById(id);
	}
	
	public Product findByIdProduct(Integer pro_id) {
		return productRepository.findById(pro_id).get();
	}



	

}