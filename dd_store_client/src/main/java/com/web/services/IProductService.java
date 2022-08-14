package com.web.services;

import java.util.List;

import com.web.model.ProductModel;
import com.web.model.ResponseObjectModel;

public interface IProductService {
	/**
	 * Gọi api lấy list các đối tượng
	 * 
	 * @param url đến API
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<List<ProductModel>> getAllProduct(String url);
	
	/**
	 * Gọi api lấy một đối tượng theo id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần lấy
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<ProductModel> getOneProduct(String url, int id);
	
	/**
	 * Gọi api Thêm đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần thêm
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<ProductModel> createProduct(String url, ProductModel productModel);

	/**
	 * Gọi api Sửa đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần sửa, bao gồm ID
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<ProductModel> updateProduct(String url, ProductModel productModel);

	/**
	 * Gọi api Xóa đối tượng với id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần xóa
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<ProductModel> deleteProduct(String url, int proId);

	/**
	 * Gọi API lấy file pdf
	 * 
	 * @param url đến API
	 * @return file PDF chứa thông tin sinh viên
	 */
	
	
}
