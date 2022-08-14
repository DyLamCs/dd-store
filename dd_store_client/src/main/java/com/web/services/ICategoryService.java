package com.web.services;

import java.util.List;

import com.web.model.CategoryModel;
import com.web.model.ResponseObjectModel;

public interface ICategoryService {
	/**
	 * Gọi api lấy list các đối tượng
	 * 
	 * @param url đến API
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<List<CategoryModel>> getCategory(String url);
	/**
	 * Gọi api lấy một đối tượng theo id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần lấy
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<CategoryModel> getOneCategory(String url, int id);
	
	/**
	 * Gọi api Thêm đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần thêm
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<CategoryModel> createCategory(String url, CategoryModel categoryModel);

	/**
	 * Gọi api Sửa đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần sửa, bao gồm ID
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<CategoryModel> updateCategory(String url, CategoryModel categoryModel);

	/**
	 * Gọi api Xóa đối tượng với id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần xóa
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<CategoryModel> deleteCategory(String url, int Id);
	
}
