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
import com.web.model.ProductModel;
import com.web.model.ResponseObjectModel;
import com.web.services.IProductService;

/**
 * Lớp thực thi của interface IProductService
 * @author IVS
 *
 */
@Service
public class ProductServiceImpl implements IProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
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
	 * Lấy ra tất cả sản phẩm
	 */
	@Override
	public ResponseObjectModel<List<ProductModel>> getAllProduct(String url) {
		// Tạo ResponseObjectModel<List<ProductModel>> rỗng
				ResponseObjectModel<List<ProductModel>> responseObject = new ResponseObjectModel<List<ProductModel>>();
				String answer;
				try {
					// Lấy chuỗi json từ url
					try {
						answer = restTemplate.getForObject(url, String.class);
					} catch (HttpClientErrorException e) {
						answer = e.getResponseBodyAsString();
					}

					// Đọc chuỗi json sang object tương ứng
					responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<List<ProductModel>>>() {
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
	 * Lấy ra một sản phẩm thông qua id
	 */
	@Override
	public ResponseObjectModel<ProductModel> getOneProduct(String url, int id) {
		// Tạo ResponseObjectModel<ProductModel> rỗng
				ResponseObjectModel<ProductModel> responseObject = new ResponseObjectModel<ProductModel>();
						String answer;
						try {
							// Gửi yêu cầu HTTP GET đến url nhận chuỗi json
							try {
								answer = restTemplate.getForObject(url + id, String.class);
							} catch (HttpClientErrorException e) {
								answer = e.getResponseBodyAsString();
							}

							// Đọc chuỗi json sang object tương ứng
							responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<ProductModel>>() {});

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
	 * Thêm mới một sản phẩm
	 */
	@Override
	public ResponseObjectModel<ProductModel> createProduct(String url, ProductModel productModel) {
		// Tạo ResponseObject<ProductModel> rỗng
		ResponseObjectModel<ProductModel> responseObject = new ResponseObjectModel<ProductModel>();
		String answer;
		try {
			// Đổi đối tượng thành chuỗi json
			String jsonProduct = objectMapper.writeValueAsString(productModel);

			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(jsonProduct, headers);
			// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
			try {
				answer = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<ProductModel>>() {
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
	 * Cập nhật thông tin của một sản phẩm
	 */
	@Override
	public ResponseObjectModel<ProductModel> updateProduct(String url, ProductModel productModel) {
		// Tạo ResponseObject<ProductModel> rỗng
		ResponseObjectModel<ProductModel> responseObject = new ResponseObjectModel<ProductModel>();
		try {
			// Đổi đối tượng thành chuỗi json
			String jsonProduct = objectMapper.writeValueAsString(productModel);

			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(jsonProduct, headers);
			// Gửi yêu cầu HTTP PUT đến server và nhận chuỗi json trả về
			@SuppressWarnings("rawtypes")
			ResponseEntity<ResponseObjectModel> answer = restTemplate.exchange(url + productModel.getProId(),
					HttpMethod.PUT, entity, ResponseObjectModel.class);
			if (answer != null && answer.getStatusCode() == HttpStatus.OK) {
				String update = new ObjectMapper().writeValueAsString(answer.getBody());
				// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
				responseObject = objectMapper.readValue(update, new TypeReference<ResponseObjectModel<ProductModel>>() {
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
	 * Xóa một sản phẩm (luận li)
	 */
	@Override
	public ResponseObjectModel<ProductModel> deleteProduct(String url, int proId) {
		// Tạo ResponseObject<ProductModel> rỗng
		ResponseObjectModel<ProductModel> responseObject = new ResponseObjectModel<ProductModel>();
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
				answer = restTemplate.exchange(url + proId, HttpMethod.DELETE, entity, String.class).getBody();
			} catch (HttpClientErrorException e) {
				answer = e.getResponseBodyAsString();
			}

			// Map chuỗi json sang đối tượng ResponseObject<StudentModel>
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObjectModel<ProductModel>>() {
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
