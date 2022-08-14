package com.web.services;

import java.util.List;

import com.web.entities.Detailbill;

public interface IDetailBillService {

	// khai báo các phương thức tương tác với CSDL
	public int create(Integer idBill, Integer pro_id, Integer pro_quantity);
	public List<Detailbill> findAllByIdBill(Integer idBill);
	public Detailbill finDetailbillByIdProduct(int pro_id, int idBill);
	public int updateDetailBillNameSake(int quantity,int pro_id, int idBill);
}
