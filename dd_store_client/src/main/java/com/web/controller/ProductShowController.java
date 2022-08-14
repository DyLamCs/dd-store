package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.BrandModel;
import com.web.model.CategoryModel;
import com.web.model.ProductModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IBrandService;
import com.web.services.ICategoryService;
import com.web.services.IProductShowAllService;

/**
 * Controller để view sản phẩm lên trang
 * @author IVS
 *
 */
@Controller
@RequestMapping("/productshow")
public class ProductShowController {

	 
	@Autowired
	IProductShowAllService ProductShowAllService;
	@Autowired
	IBrandService iBrandService;
	@Autowired
	ICategoryService iCategoryService;
	
	// Các đường dẫn để kết nối giữa clident và api
	String URL_GET_PRODUCT = "http://localhost:8081/api/product/";
	String URL_GET_BRAND = "http://localhost:8081/api/brand";
	String URL_GET_CATEGORY = "http://localhost:8081/api/category";
	String URL_GET_IMGPRODUCT = "http://localhost:8081/api/imgproduct";
	
	
	
	/**
	 * Khi có request HTTP GET đến "/student"
	 * 
	 * @param model có các phương thức giúp thêm dữ liệu vào model với key và value
	 * @return student.jsp view
	 */

	@GetMapping() 
	public String getIndex(Model model) { 
	 // Gọi api lấy list các đối tượng 
	 ResponseObjectModel<List<BrandModel>> responseObjectBrand = iBrandService.getBrand(URL_GET_BRAND);
	 ResponseObjectModel<List<CategoryModel>> responseObjectCategory = iCategoryService .getCategory(URL_GET_CATEGORY);
	// Gọi api lấy list các đối tượng 
	 ResponseObjectModel<List<ProductModel>>responseObject = ProductShowAllService.ShowAllProduct(URL_GET_PRODUCT);
	
	 
	 
	// Nếu status là "OK" trả về page /student, ngược lại trả về page exception_page
	if (responseObject!= null &&
	 responseObject.getStatus().equalsIgnoreCase("OK") &&
	 responseObjectBrand.getStatus().equalsIgnoreCase("OK") &&
	 responseObjectCategory.getStatus().equalsIgnoreCase("OK")) {
	 List<ProductModel> listProductModels = responseObject.getData();
	 List<BrandModel> listBrandModels = responseObjectBrand.getData();
	 List<CategoryModel> listCategoryModels = responseObjectCategory.getData();
	
	 
	 model.addAttribute("product", listProductModels); model.addAttribute("brand",listBrandModels); model.addAttribute("category", listCategoryModels);
	 	return "productshow/productshow";
	} else { model.addAttribute("exception_str", responseObject.getMessage());
		return "exception_page"; 
	} }

	@GetMapping("/productdetail/{id}")
	public String Productdetail(@PathVariable("id") Integer id, Model model){
		ProductModel product = ProductShowAllService.ShowOneProduct(URL_GET_PRODUCT, id).getData();
		model.addAttribute("products", product);
		return "productshow/productdetail";
	}
	
	
	
	
	
	
}
