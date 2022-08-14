package com.web.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.model.AccountModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IAccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Lớp thực thi của interface IAccountService
 * @author IVS
 *
 */
@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession session;
	
	String URL_GET_PROFILE = "http://localhost:8081/api/account/profile";
	/**
	 * Đối tượng để ghi log từ thư viện slf4j
	 */
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	/**
	 * Thực hiện các yêu cầu HTTP
	 */
	private static RestTemplate restTemplate = new RestTemplate();
	/**
	 * Thuộc thư viện jackson Cung cấp các chức năng đọc ghi JSON thành POJO object
	 * cơ bản ACCEPT_EMPTY_STRING_AS_NULL_OBJECT: Chấp nhận chuỗi rỗng là đối tượng
	 * đầy đủ
	 */
	private static ObjectMapper objectMapper = new ObjectMapper()
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

	/**
	 * Gọi api lấy list các đối tượng
	 * 
	 * @param url đến API
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	@Override
	public ResponseObjectModel<List<AccountModel>> getAccount(String URL) {
		// Tạo ResponseObjectModel<List<AccountModel>> rỗng
		ResponseObjectModel<List<AccountModel>> responseObject = new ResponseObjectModel<List<AccountModel>>();
		String answer;
		try {
			// Lấy chuỗi json từ url
			try {
				answer = restTemplate.getForObject(URL, String.class);
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Đọc chuỗi json sang object tương ứng
			responseObject = objectMapper.readValue(answer,
					new TypeReference<ResponseObjectModel<List<AccountModel>>>() {
					});

		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	/**
	 * Gọi api lấy một đối tượng theo id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần lấy
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	@Override
	public ResponseObjectModel<AccountModel> getOneAccount(String URL, int id) {
		// Tạo ResponseObjectModel<StudentModel> rỗng
		ResponseObjectModel<AccountModel> responseObject = new ResponseObjectModel<AccountModel>();
		String answer;
		try {
			// Gửi yêu cầu HTTP GET đến url nhận chuỗi json
			try {
				answer = restTemplate.getForObject(URL + id, String.class);
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Đọc chuỗi json sang object tương ứng
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<AccountModel>>() {
			});

		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	/**
	 * Gọi api Thêm đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần thêm
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	@Override
	public ResponseObjectModel<AccountModel> createAccount(String URL, AccountModel accountModel) {
		// Tạo ResponseObject<StudentModel> rỗng
		ResponseObjectModel<AccountModel> responseObject = new ResponseObjectModel<AccountModel>();
		String answer;
		try {
			// Đổi đối tượng thành chuỗi json
			String jsonAccount = objectMapper.writeValueAsString(accountModel);

			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(jsonAccount, headers);
			// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
			try {
				answer = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<AccountModel>>() {
			});

		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	/**
	 * Gọi api Sửa đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần sửa, bao gồm ID
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	@Override
	public ResponseObjectModel<AccountModel> updateAccount(String URL, AccountModel accountModel) {
		// Tạo ResponseObject<StudentModel> rỗng
		ResponseObjectModel<AccountModel> responseObject = new ResponseObjectModel<AccountModel>();
		try {
			// Đổi đối tượng thành chuỗi json
			String jsonAccount = objectMapper.writeValueAsString(accountModel);

			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(jsonAccount, headers);
			// Gửi yêu cầu HTTP PUT đến server và nhận chuỗi json trả về
			ResponseEntity<ResponseObjectModel> answer = restTemplate.exchange(URL + accountModel.getAccId(),
					HttpMethod.PUT, entity, ResponseObjectModel.class);
			if (answer != null && answer.getStatusCode() == HttpStatus.OK) {
				String update = new ObjectMapper().writeValueAsString(answer.getBody());
				// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
				responseObject = objectMapper.readValue(update, new TypeReference<ResponseObjectModel<AccountModel>>() {
				});
				return responseObject;
			}

		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	/**
	 * Gọi api Xóa đối tượng với id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần xóa
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	@Override
	public ResponseObjectModel<AccountModel> deleteAccount(String URL, int accId) {
		// Tạo ResponseObject<AccountModel> rỗng
		ResponseObjectModel<AccountModel> responseObject = new ResponseObjectModel<AccountModel>();
		String answer;
		try {
			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
			// Gửi yêu cầu HTTP DELETE đến server và nhận chuỗi json trả về
			try {
				answer = restTemplate.exchange(URL + accId, HttpMethod.DELETE, entity, String.class).getBody();
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<AccountModel>>() {
			});

		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	@Override
	public String checkCookie(String cookieName, Cookie[] cookies) {
		if (cookies != null && cookies.length > 0)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(cookieName)) {
					return cookie.getValue();
				}
			}
		return "";
	}

	@Override
	public boolean setCookie(String cookie) {
		ResponseObjectModel<AccountModel> acModel = getProfile(cookie);
		User user=null;
		if ("OK".equalsIgnoreCase(acModel.getStatus())) {
			user = parseInfoToAccountModel(acModel.getData());
		}
		
		if (user == null) {
			return false;
		} else {
			PreAuthenticatedAuthenticationToken authToken = new PreAuthenticatedAuthenticationToken(user, cookie,
					user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authToken);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			authentication.setAuthenticated(true);
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT", context);
			SecurityContextHolder.setContext(context);

			response.addCookie(new Cookie("API_JSESSIONID", cookie));
			return true;
		}
	}

	public User parseInfoToAccountModel(AccountModel info) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(info.getAccRole()));

		User user = new User(info.getAccName(),info.getAccPassword(), authorities);

		return user;

	}

	@Override
	public ResponseObjectModel<AccountModel> getProfile(String cookie) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObjectModel<AccountModel> responseObject = new ResponseObjectModel<AccountModel>();
		String answer;
		try {
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			headers.add(HttpHeaders.COOKIE, cookie);
			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
			// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
			try {
				answer = restTemplate.exchange(URL_GET_PROFILE, HttpMethod.GET, entity, String.class).getBody();
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}
			if (answer != null) {
				responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<AccountModel>>() {
				});
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	/**
	 * login cho những trang cần có quyền truy cập
	 */
	@Override
	public ResponseObjectModel<AccountModel> login(AccountModel accountModel) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObjectModel<AccountModel> responseObject = new ResponseObjectModel<AccountModel>();

		try {
			String str_auth = accountModel.getAccName() + ":" + accountModel.getAccPassword();
			//Đổi chuổi tài khoản + mk thành mảng byte
			byte[] message = str_auth.getBytes();
			String encoding = "Basic " + Base64.getEncoder().encodeToString(message);

			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			headers.add(HttpHeaders.AUTHORIZATION, encoding);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
			// Gửi yêu cầu HTTP PUT đến server và nhận chuỗi json trả về
		
			HttpEntity<String> answer = restTemplate.exchange(URL_GET_PROFILE, HttpMethod.GET, entity, String.class);
			if (answer != null) {

				responseObject = objectMapper.readValue(answer.getBody(),
						new TypeReference<ResponseObjectModel<AccountModel>>() {
						});
				logger.info("Login successful, username: " + accountModel.getAccName());
				HttpHeaders headersResponse = answer.getHeaders();
				String set_cookie = headersResponse.getFirst(HttpHeaders.SET_COOKIE);
				String[] cookieStrings = set_cookie.split(";");
				String cookieJsessionId = cookieStrings[0];
				setCookie(cookieJsessionId);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	/**
	 * Đăng ký tài khoản cho người dùng nếu chưa có tài khoản
	 */
	@Override
	public ResponseObjectModel<AccountModel> signup(String url, AccountModel accountModel) {
		// Tạo ResponseObject<StudentModel> rỗng
				ResponseObjectModel<AccountModel> responseObject = new ResponseObjectModel<AccountModel>();
				String answer;
				try {
					// Đổi đối tượng thành chuỗi json
					String jsonAccount = objectMapper.writeValueAsString(accountModel);

					// Tạo Header cho http gửi đến server API
					HttpHeaders headers = new HttpHeaders();
					// Kiểu trang là json
					headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

					// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
					HttpEntity<String> entity = new HttpEntity<String>(jsonAccount, headers);
					// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
					try {
						answer = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
					} catch (HttpClientErrorException e) {
						answer = e.getResponseBodyAsString();
					}

					// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
					responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<AccountModel>>() {
					});

				} catch (JsonParseException e) {
					logger.error(e.getMessage());
				} catch (JsonMappingException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
				// return
				return responseObject;
	}
}
