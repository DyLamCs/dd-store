package com.web.services.impl;



import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.web.entities.Account;
import com.web.entities.Bill;
import com.web.repositories.BillRepository;
import com.web.repositories.DetailBillRepository;
import com.web.services.IAccountService;
import com.web.services.IBillService;
import com.web.services.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl  implements IBillService {

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	IAccountService AccountService;
	
	@Autowired
	DetailBillRepository detailBillRepository;
	
	@Autowired
	IProductService ProductService;
	 
	
	/**
	 * tạo bill trả về null nếu không tạo được, ngược lại trả về bill
	 */
	@Override
	public Bill create(Integer acc_id) {
		//tim account theo id
		Optional<Account> account = AccountService.findById(acc_id);
		// neu ton tai account thi set vaof bill vaf thu hienj create bill
		if(account.isPresent()) {
			Bill createbill = new Bill();
			createbill.setAccount(account.get());
			return billRepository.save(createbill);
		}
		return null;
	}
	

	/**
	 * kiểm tra bill có tồn tại hay và ở trang thái chưa thanh toán và trả về bill
	 */
	@Override
	public Bill checkBill(int acc_id) {
		
		return billRepository.checkBill(acc_id);
	}

	/**
	 * xóa bill (cập nhật trạng thái deleted=1)
	 */
	@Override
	public int deleteBill(int idBill) {
		return billRepository.deleteBill(idBill);
	}

	/**
	 *
	 */
	@Override
	public int payBill(int idBill) {
		return 0;
	}

	/**
	 * Xóa bill
	 */
	@Override
	public List<Bill> findAllBiBillDeleted(int deleted) {
		
		return billRepository.findAllByBillDeleted(deleted);
	}

	@Override
	public Bill findByIdBill(Integer idBill) {
		return billRepository.findById(idBill).get();
	}

	@Override
	public void update(Bill bill) {
		billRepository.updateBill(bill.getAccount().getAccId(), bill.getId());
		
	}

	@Override
	public List<Map<String, String>> findTest() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
