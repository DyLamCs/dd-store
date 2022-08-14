package com.web.services;

import java.util.List;

import com.web.model.CategoryModel;
import com.web.model.ProductModel;
import com.web.model.ResponseObjectModel;

public interface IProductShowAllService {
	/**
	 * Gọi api lấy list các đối tượng
	 * 
	 * @param url đến API
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<List<ProductModel>> ShowAllProduct(String url);
	/**
	 * Gọi api lấy một đối tượng theo id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần lấy
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<ProductModel> ShowOneProduct(String url, int id);
	
	/**
	 * Gọi api lấy list các đối tượng
	 * 
	 * @param url đến API
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<List<CategoryModel>> ShowAllCategory(String url);
	
	public ResponseObjectModel<CategoryModel> ShowOneCategory(String url, int id);
	
	
}
