package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//@Controller là chú thích chính cho biết lớp được chú thích đóng vai trò là Bộ điều khiển của MVC
//@RequestMapping để ánh xạ các yêu cầu tới các phương thức của bộ điều khiển
/**
 * Điều hướng qua trang đăng ký
 * @author IVS
 *
 */
@Controller
@RequestMapping("/signup")
public class SignupController {
	
	
	@GetMapping()
	public String signup() {
		return"signup";
		
	}
	

	

}
