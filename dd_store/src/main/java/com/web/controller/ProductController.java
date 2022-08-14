package com.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.Product;
import com.web.entities.ResponseObject;
import com.web.services.IProductService;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api")
public class ProductController {
	@Autowired
	IProductService iproductService;
	
	/** Tim tat ca cac doi tuong co trong DB
	 * @return Danh sach cac product
	 */
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject>  readAllProducts() {
		List<Product> product = iproductService.findAll();
		if (product!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All Product successfully", product));
					
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find product", ""));
		
	}
	
	/**
	 * Tim san pham qua id (lấy đối tượng có id tương ứng với id truyền vào)
	 * @param Id của đối tượng cần tìm, lấy từ url
	 * @return đối tượng student với id tương ứng 
	 */
	@RequestMapping(value = "/product/{proId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> findById(@PathVariable(value = "proId") Integer Id) {
		Optional<Product> product = iproductService.findById(Id);
		if (product.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All Product successfully", product));
					
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find product", ""));
	}
	
	/** Them moi mot doi tuong vao DB
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> createProduct(@RequestBody Product product) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		product = iproductService.create(product);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All Product successfully", product));
		
	}
	
	
	/**
	 * Update doi tuong
	 * @param Id đối tượng cần update
	 * @param studentDetails thông tin chi tiết của đối tượng student
	 * @return 
	 */
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product productDetails, @PathVariable(value = "productId") Integer Id ) {
		// Lấy sinh viên từ DB theo id
		Optional<Product> productOptional = iproductService.findById(Id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (productOptional.isPresent()) {
			Product product =  iproductService.update(productDetails, Id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All Product successfully", product));
			
		}
		
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find product with id " + Id, ""));
	}

	 
	/**
	 * Xóa đối tượng trong DB thông qua Id
	 * @param Id của đối tượng cần xóa
	 */
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseObject> deleteProduct(@PathVariable(value = "productId") Integer Id) {
		// Lấy sinh viên từ DB theo id
		Optional<Product> productOptional = iproductService.findById(Id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (productOptional.isPresent()) {
			Product product = iproductService.delete(Id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete Product successfully", product));
			
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find product with id " + Id, ""));
	}
}