package com.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.Category;
import com.web.entities.ResponseObject;
import com.web.services.ICategoriesService;

@RestController
@RequestMapping ("api")
public class CategoriesController {
	@Autowired
	ICategoriesService iCategoriesService;
	
	/** Tim tat ca cac doi tuong co trong DB
	 * @return Danh sach cac product
	 */
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject>  readAll() {
		List<Category> categories = iCategoriesService.findAll();
		if (categories!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All category successfully", categories));
					
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find category", ""));
		
	}
	
	/**
	 * Tim san pham qua id (lấy đối tượng có id tương ứng với id truyền vào)
	 * @param Id của đối tượng cần tìm, lấy từ url
	 * @return đối tượng student với id tương ứng 
	 */
	@RequestMapping(value = "/category/{ctgId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> findById(@PathVariable(value = "ctgId") Integer Id) {
		
		Optional<Category> caterogyOptional = iCategoriesService.findById(Id);
		
		if (caterogyOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All category successfully", caterogyOptional));
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find category", ""));
		
	}	
	/** Them moi mot doi tuong vao DB
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> create(@RequestBody Category category) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		category = iCategoriesService.create(category);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All category successfully", category));
	}
	
	
	/**
	 * Update doi tuong
	 * @param Id đối tượng cần update
	 * @param studentDetails thông tin chi tiết của đối tượng student
	 * @return 
	 */
	
	@RequestMapping(value = "/category/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseObject> update(@RequestBody Category categoryDetails, @PathVariable(value = "Id") Integer Id) {
		// Lấy sinh viên từ DB theo id
		Optional<Category> categoryOptional = iCategoriesService.findById(Id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (categoryOptional.isPresent()) {
			Category category = iCategoriesService.update(categoryDetails, Id);
					
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All category successfully", category));
					
			}
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find category with id " + Id, ""));
	}

	 
	/**
	 * Xóa luận lí đối tượng trong DB thông qua Id
	 * @param Id của đối tượng cần xóa
	 */
	@RequestMapping(value = "/category/{ctgId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "ctgId") Integer Id) {
		iCategoriesService.delete(Id);
	}
}
