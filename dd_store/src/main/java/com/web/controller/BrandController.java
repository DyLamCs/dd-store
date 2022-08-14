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

import com.web.entities.Brand;
import com.web.entities.ResponseObject;
import com.web.services.IBrandService;

@RestController
@RequestMapping ("/api")
public class BrandController {
	@Autowired
	IBrandService iBrandService;
	
	
	/** Tim tat ca cac doi tuong co trong DB
	 * @return Danh sach cac product
	 */
	@RequestMapping(value = "/brand", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject>  readAll() {
		List<Brand> brands = iBrandService.findAll();
		if (brands!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All brand successfully", brands));
					
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find brand", ""));
		
	}
	
	/**
	 * Tim san pham qua id (lấy đối tượng có id tương ứng với id truyền vào)
	 * @param Id của đối tượng cần tìm, lấy từ url
	 * @return đối tượng student với id tương ứng 
	 */
	@RequestMapping(value = "/brand/{braId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> findById(@PathVariable(value = "braId") Integer Id) {
		
		Optional<Brand> brandOptional = iBrandService.findById(Id);
		
		if (brandOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All brand successfully", brandOptional));
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find brand", ""));
		
	}	
	/** Them moi mot doi tuong vao DB
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/brand", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> createBrand(@RequestBody Brand brand) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		brand = iBrandService.create(brand);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All brand successfully", brand));
	}
	
	
	/**
	 * Update doi tuong
	 * @param Id đối tượng cần update
	 * @param studentDetails thông tin chi tiết của đối tượng student
	 * @return 
	 */
	
	@RequestMapping(value = "/brand/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseObject> updateAccount(@RequestBody Brand BranDtails, @PathVariable(value = "Id") Integer Id) {
		// Lấy sinh viên từ DB theo id
		Optional<Brand> brandOptional = iBrandService.findById(Id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (brandOptional.isPresent()) {
//			Brand brand =  iBrandService.update(brandOptional, Id);
			Brand brand = iBrandService.update(BranDtails, Id);
					
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All brand successfully", brand));
					
			}
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find brand with id " + Id, ""));
	}

	 
	/**
	 * Xóa luận lí đối tượng trong DB thông qua Id
	 * @param Id của đối tượng cần xóa
	 */
	@RequestMapping(value = "/brand/{braId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "braId") Integer Id) {
		iBrandService.delete(Id);
	}

}





