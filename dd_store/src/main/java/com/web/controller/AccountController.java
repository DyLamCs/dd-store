package com.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.Account;
import com.web.entities.ResponseObject;
import com.web.services.IAccountService;

@RestController
@RequestMapping ("/api")
public class AccountController {
	
	@Autowired
	IAccountService iAccountService;
	
	/** Tim tat ca cac doi tuong co trong DB
	 * @return Danh sach cac product
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject>  readAll() {
		List<Account> accounts = iAccountService.findAll();
		if (accounts!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All account successfully", accounts));
					
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find account", ""));
		
	}
	
	/**
	 * Tim san pham qua id (lấy đối tượng có id tương ứng với id truyền vào)
	 * @param Id của đối tượng cần tìm, lấy từ url
	 * @return đối tượng student với id tương ứng 
	 */
	@RequestMapping(value = "/account/{accId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> findById(@PathVariable(value = "accId") Integer Id) {
		
		Optional<Account> accOptional = iAccountService.findById(Id);
		
		if (accOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All account successfully", accOptional));
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find account", ""));
		
	}
	
	/**
	 * Lấy thông tin của tài khoản
	 * @return
	 */
	@RequestMapping(value = "/account/profile", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getMyProfile() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accName = authentication.getName();
		if (!"anonymousUser".equalsIgnoreCase(accName)) {
			Optional<Account> accOptional = iAccountService.findByAccName(accName);
			
			if (accOptional.isPresent()) {
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All account successfully", accOptional.get()));
				
			}
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find account", ""));
		
	}
	/** 
	 * Them moi mot doi tuong vao DB
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> createAccount(@RequestBody Account account) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		account = iAccountService.create(account);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All account successfully", account));
	}
	
	/**
	 * Đăng ký mới tài khoản cho người dùng với ROLE = ROLE_USER
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> createUser(@RequestBody Account account) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		account = iAccountService.signup(account);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All account successfully", account));
	}
	
	
	/**
	 * Update doi tuong
	 * @param Id đối tượng cần update
	 * @param studentDetails thông tin chi tiết của đối tượng student
	 * @return 
	 */
	
	@RequestMapping(value = "/account/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseObject> updateAccount(@RequestBody Account accountDetails, @PathVariable(value = "Id") Integer Id) {
		// Lấy sinh viên từ DB theo id
		Optional<Account> accOptional = iAccountService.findById(Id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (accOptional.isPresent()) {
			Account account =  iAccountService.update(accountDetails, Id);
					
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get All account successfully", account));
					
			}
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find account with id " + Id, ""));
	}

	 
	/**
	 * Xóa luận lí đối tượng trong DB thông qua Id
	 * @param Id của đối tượng cần xóa
	 */
	@RequestMapping(value = "/account/{accId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseObject> deleteAccount(@PathVariable(value = "accId") Integer Id) {
		// Lấy sinh viên từ DB theo id
		Optional<Account> accOptional = iAccountService.findById(Id);
		
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (accOptional.isPresent()) {
			Account account = accOptional.get();
			account.setAccDeleted((byte)1);
			updateAccount(account, Id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete account successfully", ""));
					
		}
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find account with id " + Id, ""));
	}

}
