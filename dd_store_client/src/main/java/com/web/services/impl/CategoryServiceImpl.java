package com.web.services.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.web.model.CategoryModel;
import com.web.model.ResponseObjectModel;
import com.web.services.ICategoryService;

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

/**
 * Lớp thực thi của interface ICategoryService
 * @author IVS
 *
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
	/**
	 * Đối tượng để ghi log từ thư viện slf4j
	 */
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

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
	public ResponseObjectModel<List<CategoryModel>> getCategory(String URL) {
		// Tạo ResponseObjectModel<List<BrandModel>> rỗng
				ResponseObjectModel<List<CategoryModel>> responseObject = new ResponseObjectModel<List<CategoryModel>>();
				String answer;
				try {
					// Lấy chuỗi json từ url
					try {
						answer = restTemplate.getForObject(URL, String.class);
					} catch (HttpClientErrorException e) {
						answer = e.getResponseBodyAsString();
					}
					// Đọc chuỗi json sang object tương ứng
					responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<List<CategoryModel>>>() {
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
	public ResponseObjectModel<CategoryModel> getOneCategory(String URL, int id) {
		// Tạo ResponseObjectModel<StudentModel> rỗng
				ResponseObjectModel<CategoryModel> responseObject = new ResponseObjectModel<CategoryModel>();
				String answer;
				try {
					// Gửi yêu cầu HTTP GET đến url nhận chuỗi json
					try {
						answer = restTemplate.getForObject(URL + id, String.class);
					} catch (HttpClientErrorException e) {
						answer = e.getResponseBodyAsString();
					}

					// Đọc chuỗi json sang object tương ứng
					responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<CategoryModel>>() {
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
	public ResponseObjectModel<CategoryModel> createCategory(String URL, CategoryModel categoryModel) {
		// Tạo ResponseObject<StudentModel> rỗng
				ResponseObjectModel<CategoryModel> responseObject = new ResponseObjectModel<CategoryModel>();
				String answer;
				try {
					// Đổi đối tượng thành chuỗi json
					String jsonCategory = objectMapper.writeValueAsString(categoryModel);

					// Tạo Header cho http gửi đến server API
					HttpHeaders headers = new HttpHeaders();
					// Kiểu trang là json
					headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

					// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
					HttpEntity<String> entity = new HttpEntity<String>(jsonCategory, headers);
					// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
					try {
						answer = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
					} catch (HttpClientErrorException e) {
						answer = e.getResponseBodyAsString();
					}

					// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
					responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<CategoryModel>>() {
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
	public ResponseObjectModel<CategoryModel> updateCategory(String URL, CategoryModel categoryModel) {
		// Tạo ResponseObject<StudentModel> rỗng
				ResponseObjectModel<CategoryModel> responseObject = new ResponseObjectModel<CategoryModel>();
				try {
					// Đổi đối tượng thành chuỗi json
					String jsonCategory = objectMapper.writeValueAsString(categoryModel);

					// Tạo Header cho http gửi đến server API
					HttpHeaders headers = new HttpHeaders();
					// Kiểu trang là json
					headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

					// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
					HttpEntity<String> entity = new HttpEntity<String>(jsonCategory, headers);
					// Gửi yêu cầu HTTP PUT đến server và nhận chuỗi json trả về
					ResponseEntity<ResponseObjectModel> answer = restTemplate.exchange(URL + categoryModel.getCtgId(),
							HttpMethod.PUT, entity, ResponseObjectModel.class);
					if (answer != null && answer.getStatusCode() == HttpStatus.OK) {
						String update = new ObjectMapper().writeValueAsString(answer.getBody());
						// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
						responseObject = objectMapper.readValue(update, new TypeReference<ResponseObjectModel<CategoryModel>>() {
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

	@Override
	public ResponseObjectModel<CategoryModel> deleteCategory(String URL, int Id) {
		// Tạo ResponseObject<AccountModel> rỗng
				ResponseObjectModel<CategoryModel> responseObject = new ResponseObjectModel<CategoryModel>();
				String answer;
				try {
					// Tạo Header cho http gửi đến server API
					HttpHeaders headers = new HttpHeaders();
					// Kiểu trang là json
					headers.setContentType(MediaType.APPLICATION_JSON);

					// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
					HttpEntity<String> entity = new HttpEntity<String>(null, headers);
					// Gửi yêu cầu HTTP DELETE đến server và nhận chuỗi json trả về
				
					answer = restTemplate.exchange(URL + Id, HttpMethod.DELETE, entity, String.class).getBody();
					/*
					 * if(answer == null) { ResponseObjectModel<CategoryModel> goi =
					 * getOneCategory(URL , Id ); if(goi != null &&
					 * goi.getStatus().equalsIgnoreCase("OK")) { return new
					 * ResponseObjectModel<CategoryModel>("OK", "", null);
					 * 
					 * } } return new ResponseObjectModel<CategoryModel>("Flase", "", null);
					 */
				} catch (Exception e) {
					logger.error(e.getMessage());
				} 
				// return
				return responseObject;
	}
	
}
