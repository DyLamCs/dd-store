package com.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.AccountModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IAccountService;

//@Controller là chú thích chính cho biết lớp được chú thích đóng vai trò là Bộ điều khiển của MVC
//@RequestMapping để ánh xạ các yêu cầu tới các phương thức của bộ điều khiển
/**
 * Bộ điều khiển của chức năng login
 * @author IVS
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	IAccountService accountService;

	/**
	 * Điều hướng khi đăng nhập
	 * @param request
	 * @return
	 */
	@GetMapping
	public String getLogin(HttpServletRequest request) {
		// kiểm tra xác thực
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// anonymousUser là user mặc định của Spring
		if (!"anonymousUser".equalsIgnoreCase(authentication.getName())) {
			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
				return "redirect:/account";
			else
				return "redirect:/productshow";
		}
		return "login";
	}

	/**
	 * Kiểm tra quyền và điều hướng khi đăng nhập
	 * @param account
	 * @param model
	 * @param request 
	 * @param session
	 * @return view
	 */
	@PostMapping("/post")
	public String login(@ModelAttribute AccountModel account, Model model, HttpServletRequest request,
			HttpSession session) {
		model.addAttribute("status", "Login");
		// check cookie

		ResponseObjectModel<AccountModel> responseObject = accountService.login(account);

		if ("OK".equals(responseObject.getStatus())) {
			session.setAttribute("account", responseObject.getData());
			if (responseObject.getData().getAccRole().contains("ROLE_USER")) {
				return "redirect:/productshow";
			}
			if (responseObject.getData().getAccRole().contains("ROLE_ADMIN")) {
				return "redirect:/account";
			}

		} else if ("FALIED".equals(responseObject.getStatus())) {
			model.addAttribute("message", "ERROR: " + responseObject.getMessage());
		} else {
			model.addAttribute("message", "ERROR: Sai mat khau");
		}

		return "redirect:/productshow";

	}

	/** Đăng xuất
	 * @param request
	 * @param response
	 * @return view
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		boolean isSecure = false;
		String contextPath = null;
		if (request != null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			isSecure = request.isSecure();
			contextPath = request.getContextPath();
		}
		SecurityContext context = SecurityContextHolder.getContext();
		SecurityContextHolder.clearContext();
		context.setAuthentication(null);
		if (response != null) {
			Cookie cookie = new Cookie("API_JSESSIONID", null);
			String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
			cookie.setPath(cookiePath);
			cookie.setMaxAge(0);
			cookie.setSecure(isSecure);
			response.addCookie(cookie);
		}
		return "redirect:/productshow";
	}
}
