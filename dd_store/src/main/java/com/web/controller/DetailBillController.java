package com.web.controller;

import java.util.List;
import java.util.Map;

import com.web.entities.Detailbill;
import com.web.services.IDetailBillService;
import com.web.services.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detailbill")
public class DetailBillController {
	@Autowired
	IDetailBillService detailBillService;
	
	@Autowired
	IProductService productService;
	
	/**
	 * tìm tất cả detailbill theo idbill
	 * 
	 */
	@GetMapping("/{idBill}")
	public List<Detailbill> findAll(@PathVariable("idBill") Integer idBill){
		return detailBillService.findAllByIdBill(idBill);
	}
	
	/**
	 * tạo detailbill theo idbill,idproduct  
	 * b2: thêm hàng hóa chi tiết
	 */
	@PostMapping("/{idBill}")
	public String create(@PathVariable("idBill") Integer id, @RequestBody Map<String, Integer> map) {
		Integer idProduct = map.get("pro_id");
		Integer quantity = map.get("quantity");
		
		int check = detailBillService.create(id, idProduct, quantity);
		
		return (check != -1) ? "Them thanh cong" : "Them that bai";
		
	}
	
	
	
}
