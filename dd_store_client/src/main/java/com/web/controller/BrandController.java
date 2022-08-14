package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.BrandModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IBrandService;

/**
 * Bộ điều khiển quản lý brand
 * @author IVS
 *
 */
@Controller
@RequestMapping("/brand")

public class BrandController {
	/**
	 * Tự động tiêm impl cho interface
	 * 
	 */
	@Autowired
	IBrandService brandService;
	// Các đường dẫn để kết nối giữa clident và api
	String URL_GET_BRAND 	= "http://localhost:8081/api/brand";
	String URL_CREATE_BRAND = "http://localhost:8081/api/brand/";
	String URL_UPDATE_BRAND = "http://localhost:8081/api/brand/";
	String URL_DELETE_BRAND = "http://localhost:8081/api/brand/";
	
	/**
	 * Khi có request HTTP GET đến "/brand"
	 * 
	 * @param model có các phương thức giúp thêm dữ liệu vào model với key và value
	 * @return brand.jsp view
	 */
	@GetMapping()
	public String getIndex(Model model) {
		// Gọi api lấy list các đối tượng
		ResponseObjectModel<List<BrandModel>> responseObject = brandService.getBrand(URL_GET_BRAND);
		// Nếu status là "OK" trả về page /brand, ngược lại trả về page exception_page
		if ("OK".equalsIgnoreCase(responseObject.getStatus()) ){
			List<BrandModel> listBrandModels = responseObject.getData();
			model.addAttribute("brand", listBrandModels);
			return "brand/brand";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}
	}
	
	/**
	 * Khi có request HTTP POST đến "/add"
	 * 
	 * @param brandModel thông tin đối tượng nhận từ view
	 * @return đường dẫn /brand
	 */
	
	@PostMapping("/add")
	public String addAccount(@ModelAttribute("brand") BrandModel brandModel, Model model) {
		// Gọi api Thêm đối tượng với các thuộc tính được thay đổi
		ResponseObjectModel<BrandModel> responseObject = brandService.createBrand(URL_CREATE_BRAND, brandModel);
		// Nếu status là "OK" trả về page /brand, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return ("redirect:/brand");
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}
	}
	/**
	 * Khi có request HTTP GET đến "/update/{id}"
	 * 
	 * @param id    lấy từ đường dẫn url
	 * @param model có các phương thức giúp thêm dữ liệu vào model với key và value
	 * @return update-brand.jsp view
	 */
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		// Gọi api lấy một đối tượng với id
		ResponseObjectModel<BrandModel> responseObject = brandService.getOneBrand(URL_UPDATE_BRAND, id);
		// Nếu status là "OK" trả về page /brand, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			// Lấy data đối tượng
			BrandModel brandModel = responseObject.getData();
			// Gán đối tượng vào model
			model.addAttribute("brand", brandModel);
			return "update-brand";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}
	}

	/**
	 * Khi có request HTTP POST đến "/update/{id}"
	 * 
	 * @param  thông tin đối tượng nhận từ view
	 * @return dẫn đến "/brand" hoặc trang expception_page
	 */
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, @ModelAttribute BrandModel brandModel, Model model) {
		brandModel.setBraId(id);
		// Gọi api Sửa đối tượng với các thuộc tính được thay đổi
		ResponseObjectModel<BrandModel> responseObject = brandService.updateBrand(URL_UPDATE_BRAND, brandModel);
		// Nếu status là "OK" trả về page /brand, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return "redirect:/brand";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}

	}

	/**
	 * Khi có request HTTP POST đến "/delete/{id}"
	 * 
	 * @param id lấy từ đường dẫn url
	 * @return dẫn đến "/brand" hoặc trang expception_page
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		// Gọi api Xóa đối tượng với id
		@SuppressWarnings("unused")
		ResponseObjectModel<BrandModel> responseObject = brandService.deleteBrand(URL_DELETE_BRAND, id);
		return "redirect:/brand";
		

	}
	
	
	
}
