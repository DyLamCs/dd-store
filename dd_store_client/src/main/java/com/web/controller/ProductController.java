package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.AccountModel;
import com.web.model.BrandModel;
import com.web.model.CategoryModel;
import com.web.model.ProductModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IAccountService;
import com.web.services.IBrandService;
import com.web.services.ICategoryService;
import com.web.services.IProductService;

/**
 * Controller của bản produt để khiển service (CRUD...)
 * @author IVS
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	/**
	 * Tự động tiêm impl cho interface
	 * 
	 */
	@Autowired
	IAccountService accountService;
	@Autowired
	IProductService iProductService;
	@Autowired
	IBrandService iBrandService;
	@Autowired
	ICategoryService iCategoryService;
	// Các đường dẫn để kết nối giữa clident và api
	String URL_GET_PRODUCT = "http://localhost:8081/api/product";
	String URL_CREATE_PRODUCT = "http://localhost:8081/api/product/";
	String URL_UPDATE_PRODUCT = "http://localhost:8081/api/product/";
	String URL_DELETE_PRODUCT = "http://localhost:8081/api/product/";

	String URL_GET_BRAND = "http://localhost:8081/api/brand";
	String URL_GET_CATEGORY = "http://localhost:8081/api/category";
	String URL_GET_IMGPRODUCT = "http://localhost:8081/api/imgproduct";

	/**
	 * Khi có request HTTP GET đến "/product"
	 * 
	 * @param model có các phương thức giúp thêm dữ liệu vào model với key và value
	 * @return product.jsp view
	 */
	@GetMapping()
	public String getIndex(HttpServletRequest request, HttpSession session, Model model) {

		// lấy thông tin account ở session
		AccountModel account = (AccountModel) session.getAttribute("account");
		if (account != null) {
			// lấy quyền account
			String role = account.getAccRole();
			if (role != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
				// Gọi api lấy list các đối tượng
				ResponseObjectModel<List<BrandModel>> responseObjectBrand = iBrandService.getBrand(URL_GET_BRAND);
				ResponseObjectModel<List<CategoryModel>> responseObjectCategory = iCategoryService
						.getCategory(URL_GET_CATEGORY);

				// Gọi api lấy list các đối tượng
				ResponseObjectModel<List<ProductModel>> responseObject = iProductService.getAllProduct(URL_GET_PRODUCT);
				// Nếu status là "OK" trả về page /product, ngược lại trả về page exception_page
				if (responseObject != null && responseObject.getStatus().equalsIgnoreCase("OK")
						&& responseObjectBrand.getStatus().equalsIgnoreCase("OK")
						&& responseObjectCategory.getStatus().equalsIgnoreCase("OK")) {

					List<ProductModel> listProductModels = responseObject.getData();
					List<BrandModel> listBrandModels = responseObjectBrand.getData();
					List<CategoryModel> listCategoryModels = responseObjectCategory.getData();

					model.addAttribute("product", listProductModels);
					model.addAttribute("brand", listBrandModels);
					model.addAttribute("category", listCategoryModels);
					return "product/product";

				}

			}
		}
		return "redirect:/home";
	}

	/**
	 * Khi có request HTTP POST đến "/add"
	 * 
	 * @param productModel thông tin đối tượng nhận từ view
	 * @return đường dẫn /product
	 */
	@PostMapping("/add")
	public String addProduct(@ModelAttribute("product") ProductModel productModel, @RequestParam("brandId") int brandId,
			@RequestParam("categoryId") int categoryId, Model model) {

		productModel.getBrand().setBraId(brandId);
		productModel.getCategory().setCtgId(categoryId);

		// Gọi api Thêm đối tượng với các thuộc tính được thay đổi
		ResponseObjectModel<ProductModel> responseObject = iProductService.createProduct(URL_CREATE_PRODUCT,
				productModel);
		// Nếu status là "OK" trả về page /product-manager, ngược lại trả về page
		// exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return ("redirect:/product");
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
	 * @return update-product.jsp view
	 */

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, @ModelAttribute ProductModel productModel, Model model) {
		// Gọi api lấy một đối tượng với id
		ResponseObjectModel<ProductModel> responseObject = iProductService.getOneProduct(URL_UPDATE_PRODUCT, id);
		// Nếu status là "OK" trả về page /product, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			// Lấy data đối tượng
			productModel = responseObject.getData();
			// Gán đối tượng vào model
			model.addAttribute("product", productModel);
			return ("update-product");
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}
	}

	/**
	 * Khi có request HTTP POST đến "/update/{id}"
	 * 
	 * @param product thông tin đối tượng nhận từ view
	 * @return dẫn đến "/product" hoặc trang expception_page
	 */
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, @ModelAttribute ProductModel productModel,
			@RequestParam("brandId") int brandId, @RequestParam("categoryId") int categoryId, Model model) {

		productModel.setProId(id);
		productModel.getBrand().setBraId(brandId);
		productModel.getCategory().setCtgId(categoryId);

		// Gọi api Sửa đối tượng với các thuộc tính được thay đổi
		ResponseObjectModel<ProductModel> responseObject = iProductService.updateProduct(URL_UPDATE_PRODUCT,
				productModel);
		// Nếu status là "OK" trả về page /product, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return "redirect:/product";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}

	}

	/**
	 * Khi có request HTTP POST đến "/delete/{id}"
	 * 
	 * @param id lấy từ đường dẫn url
	 * @return dẫn đến "/product" hoặc trang expception_page
	 */
	@GetMapping("/delete/{proId}")
	public String delete(@PathVariable("proId") Integer proId, Model model) {
		// Gọi api Xóa đối tượng với id
		ResponseObjectModel<ProductModel> responseObject = iProductService.deleteProduct(URL_DELETE_PRODUCT, proId);

		// Nếu status là "OK" trả về page /product, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return "redirect:/product";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}

	}

}
