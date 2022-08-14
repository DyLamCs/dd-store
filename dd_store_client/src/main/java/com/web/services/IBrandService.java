package com.web.services;

import java.util.List;


import com.web.model.BrandModel;
import com.web.model.ResponseObjectModel;

public interface IBrandService {
	/**
	 * Gọi api lấy list các đối tượng
	 * 
	 * @param url đến API
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<List<BrandModel>> getBrand(String url);
	/**
	 * Gọi api lấy một đối tượng theo id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần lấy
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<BrandModel> getOneBrand(String url, int id);
	
	/**
	 * Gọi api Thêm đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần thêm
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<BrandModel> createBrand(String url, BrandModel brandModel);

	/**
	 * Gọi api Sửa đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần sửa, bao gồm ID
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<BrandModel> updateBrand(String url, BrandModel brandModel);

	/**
	 * Gọi api Xóa đối tượng với id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần xóa
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<BrandModel> deleteBrand(String url, int id);

}
