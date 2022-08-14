package com.web.services;

import java.util.List;
import java.util.Map;

import com.web.entities.Bill;


public interface IBillService {
	// khai báo các phương thức tương tác với CSDL
		public abstract Bill create(Integer acc_id);
		public Bill checkBill(int acc_id);
		public int deleteBill(int idBill);
		public int payBill(int idBill);
		public List<Bill> findAllBiBillDeleted(int deleted);
		public Bill findByIdBill(Integer idBill);
		public void update(Bill bill);
		public List<Map<String, String>> findTest();
}
