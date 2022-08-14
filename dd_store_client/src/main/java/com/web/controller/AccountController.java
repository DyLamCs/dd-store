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

import com.web.model.AccountModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IAccountService;

//@Controller là chú thích chính cho biết lớp được chú thích đóng vai trò là Bộ điều khiển của MVC
//@RequestMapping để ánh xạ các yêu cầu tới các phương thức của bộ điều khiển
/**
 * Bộ điều khiển quản lý account
 * @author IVS
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	/**
	 * Tự động tiêm impl cho interface
	 * 
	 */
	@Autowired
	IAccountService accountService;
	
	// Các đường dẫn để kết nối giữa clident và api
	String URL_GET_ACCOUNT = "http://localhost:8081/api/account";
	String URL_CREATE_ACCOUNT = "http://localhost:8081/api/account/";
	String URL_SIGN_UP = "http://localhost:8081/api/signup/";
	String URL_UPDATE_ACCOUNT = "http://localhost:8081/api/account/";
	String URL_DELETE_ACCOUNT = "http://localhost:8081/api/account/";

	/**
	 * Khi có request HTTP GET đến "/account"
	 * 
	 * @param model có các phương thức giúp thêm dữ liệu vào model với key và value
	 * @return account.jsp view
	 */
	@GetMapping()
	public String getIndex(Model model) {
		// Gọi api lấy list các đối tượng
		ResponseObjectModel<List<AccountModel>> responseObject = accountService.getAccount(URL_GET_ACCOUNT);
		// Nếu status là "OK" trả về page /account, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			List<AccountModel> listAccountModels = responseObject.getData();
			model.addAttribute("account", listAccountModels);
			
		}

		return "account/account";

	}

	/**
	 * Khi có request HTTP POST đến "/add"
	 * 
	 * @param studentModel thông tin đối tượng nhận từ view
	 * @return đường dẫn /account
	 */

	@PostMapping("/add")
	public String addAccount(@ModelAttribute("account") AccountModel accountModel, Model model) {
		// Gọi api Thêm đối tượng với các thuộc tính được thay đổi
		ResponseObjectModel<AccountModel> responseObject = accountService.createAccount(URL_CREATE_ACCOUNT,
				accountModel);
		// Nếu status là "OK" trả về page /student, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return ("redirect:/account");
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}
	}

	/**
	 * Thêm mới một tài khoản với vai trò là người dùng chưa có tài khoản
	 * 
	 * @param accountModel
	 * @param model
	 * @return
	 */
	@PostMapping("/signup")
	public String signup(@ModelAttribute("account") AccountModel accountModel, Model model) {
		// Gọi api Thêm đối tượng với các thuộc tính được thay đổi
		ResponseObjectModel<AccountModel> responseObject = accountService.createAccount(URL_SIGN_UP, accountModel);
		// Nếu status là "OK" trả về page /student, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return ("redirect:/login");
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
	 * @return update-account.jsp view
	 */
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		// Gọi api lấy một đối tượng với id
		ResponseObjectModel<AccountModel> responseObject = accountService.getOneAccount(URL_UPDATE_ACCOUNT, id);
		// Nếu status là "OK" trả về page /student, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			// Lấy data đối tượng
			AccountModel accountModel = responseObject.getData();
			// Gán đối tượng vào model
			model.addAttribute("account", accountModel);
			return "update-account";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}
	}

	/**
	 * Khi có request HTTP POST đến "/update/{id}"
	 * 
	 * @param student thông tin đối tượng nhận từ view
	 * @return dẫn đến "/account" hoặc trang expception_page
	 */
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, @ModelAttribute AccountModel accountModel, Model model) {
		accountModel.setAccId(id);
		// Gọi api Sửa đối tượng với các thuộc tính được thay đổi
		ResponseObjectModel<AccountModel> responseObject = accountService.updateAccount(URL_UPDATE_ACCOUNT,
				accountModel);
		// Nếu status là "OK" trả về page /student, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return "redirect:/account";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}

	}

	/**
	 * Khi có request HTTP POST đến "/delete/{id}"
	 * 
	 * @param id lấy từ đường dẫn url
	 * @return dẫn đến "/account" hoặc trang expception_page
	 */
	@GetMapping("/delete/{accId}")
	public String delete(@PathVariable("accId") Integer accId, Model model) {
		// Gọi api Xóa đối tượng với id
		ResponseObjectModel<AccountModel> responseObject = accountService.deleteAccount(URL_DELETE_ACCOUNT, accId);

		// Nếu status là "OK" trả về page /student, ngược lại trả về page exception_page
		if (responseObject.getStatus().equalsIgnoreCase("OK")) {
			return "redirect:/account";
		} else {
			model.addAttribute("exception_str", responseObject.getMessage());
			return "exception_page";
		}

	}

}