package com.web.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.model.BrandModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IBrandService;

/**
 * Lớp thực thi của interface IBrandService
 * @author IVS
 *
 */
@Service
public class BrandServiceImpl implements IBrandService {
	/**
	 * Đối tượng để ghi log từ thư viện slf4j
	 */
	private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

	/**
	 * Thực hiện các yêu cầu HTTP
	 */
	private static RestTemplate restTemplate = new RestTemplate();
	/**
	 * Thuộc thư viện jackson Cung cấp các chức năng đọc ghi JSON thành POJO object
	 * cơ bản ACCEPT_EMPTY_STRING_AS_NULL_OBJECT: Chấp nhận chuỗi rỗng là đối tượng
	 * đầy đủ
	 */
	private static ObjectMapper objectMapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

	@Override
	public ResponseObjectModel<List<BrandModel>> getBrand(String URL) {
		// Tạo ResponseObjectModel<List<BrandModel>> rỗng
		ResponseObjectModel<List<BrandModel>> responseObject = new ResponseObjectModel<List<BrandModel>>();
		String answer;
		try {
			// Lấy chuỗi json từ url
			try {
				answer = restTemplate.getForObject(URL, String.class);
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}
			// Đọc chuỗi json sang object tương ứng
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<List<BrandModel>>>() {
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
	public ResponseObjectModel<BrandModel> getOneBrand(String URL, int id) {
		// Tạo ResponseObjectModel<StudentModel> rỗng
		ResponseObjectModel<BrandModel> responseObject = new ResponseObjectModel<BrandModel>();
		String answer;
		try {
			// Gửi yêu cầu HTTP GET đến url nhận chuỗi json
			try {
				answer = restTemplate.getForObject(URL + id, String.class);
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Đọc chuỗi json sang object tương ứng
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<BrandModel>>() {
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
	public ResponseObjectModel<BrandModel> createBrand(String URL, BrandModel brandModel) {
		// Tạo ResponseObject<StudentModel> rỗng
		ResponseObjectModel<BrandModel> responseObject = new ResponseObjectModel<BrandModel>();
		String answer;
		try {
			// Đổi đối tượng thành chuỗi json
			String jsonBrand = objectMapper.writeValueAsString(brandModel);

			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(jsonBrand, headers);
			// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
			try {
				answer = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<BrandModel>>() {
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
	public ResponseObjectModel<BrandModel> updateBrand(String URL, BrandModel brandModel) {
		// Tạo ResponseObject<StudentModel> rỗng
		ResponseObjectModel<BrandModel> responseObject = new ResponseObjectModel<BrandModel>();
		try {
			// Đổi đối tượng thành chuỗi json
			String jsonBrand = objectMapper.writeValueAsString(brandModel);

			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(jsonBrand, headers);
			// Gửi yêu cầu HTTP PUT đến server và nhận chuỗi json trả về
			ResponseEntity<ResponseObjectModel> answer = restTemplate.exchange(URL + brandModel.getBraId(),
					HttpMethod.PUT, entity, ResponseObjectModel.class);
			if (answer != null && answer.getStatusCode() == HttpStatus.OK) {
				String update = new ObjectMapper().writeValueAsString(answer.getBody());
				// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
				responseObject = objectMapper.readValue(update, new TypeReference<ResponseObjectModel<BrandModel>>() {
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
	public ResponseObjectModel<BrandModel> deleteBrand(String URL, int id) {
		// Tạo ResponseObject<AccountModel> rỗng
		ResponseObjectModel<BrandModel> responseObject = new ResponseObjectModel<BrandModel>();
		String answer;
		try {
			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
			// Gửi yêu cầu HTTP DELETE đến server và nhận chuỗi json trả về
		
			answer = restTemplate.exchange(URL + id, HttpMethod.DELETE, entity, String.class).getBody();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

}
