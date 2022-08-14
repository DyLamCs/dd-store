package com.web.services.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.model.CategoryModel;
import com.web.model.ProductModel;
import com.web.model.ResponseObjectModel;

import com.web.services.IProductShowAllService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Lớp thực thi của interface IProductShowAllService
 * @author IVS
 *
 */
@Service
public class ProductShowAllServiceImpl implements IProductShowAllService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductShowAllServiceImpl.class);
	private static RestTemplate restTemplate = new RestTemplate();

	private static ObjectMapper objectMapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

	/**
	 * Lấy ra thông tin sản phẩm
	 */
	@Override
	public ResponseObjectModel<List<ProductModel>> ShowAllProduct(String url) {
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
	 * Lấy ra thông tin một sp thông qua id
	 */
	@Override
	public ResponseObjectModel<ProductModel> ShowOneProduct(String url, int id) {
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
	 * Lấy ra tất cả caterogy
	 */
	@Override
	public ResponseObjectModel<List<CategoryModel>> ShowAllCategory(String URL) {
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

	/**
	 * Lấy ra một category qua id
	 */
	@Override
	public ResponseObjectModel<CategoryModel> ShowOneCategory(String URL, int id) {
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

	
	

	
}
