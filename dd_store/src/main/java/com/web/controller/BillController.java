package com.web.controller;

import java.util.List;
import java.util.Map;

import com.web.entities.Bill;
import com.web.services.IBillService;
import com.web.services.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
	@Autowired
	IBillService iBillService;
	
	@Autowired
	IProductService productService;
	
	/**
	 * tìm tất cả bill theo trạng thái deleted
	 */
	@GetMapping("/{deleted}")
	public List<Bill> findAllBill(@PathVariable("deleted") int deleted){
		return iBillService.findAllBiBillDeleted(deleted);
	}
	
	/**
	 * tạo bill theo idAccount
	 * hành động thêm giỏ hàng
	 */
	@PostMapping("/create/{idAccount}") 
	public String createBill(@PathVariable("idAccount") Integer idAccount) {
		Bill bill = iBillService.create(idAccount);
		return (bill != null) ? "Tao thanh cong" : "Tao that bai";
	}
	
	/**
	 * thanh toán bill theo idbill, cập nhật trạng thái deleted và total
	 */
	@PostMapping("/pay/{idBill}")
	public int pay(@PathVariable("idBill") Integer idBill) {
		return iBillService.payBill( idBill);
	}
	
	@GetMapping("/test")
	public List<Map<String, String>> findTest(){
		return iBillService.findTest();
	}

	
}
