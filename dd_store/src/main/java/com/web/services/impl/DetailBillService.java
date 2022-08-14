package com.web.services.impl;



import java.util.List;

import com.web.entities.Product;
import com.web.entities.Bill;
import com.web.entities.Detailbill;

import com.web.repositories.DetailBillRepository;
import com.web.services.IBillService;
import com.web.services.IDetailBillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DetailBillService implements IDetailBillService{

	@Autowired
	IBillService billService;
	
	@Autowired
	DetailBillRepository detailBillRepository;
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	/* tạo detailbill
	 */
	@Override
	public int create(Integer idBill, Integer pro_id, Integer pro_quantity) {
				// tìm sản phẩm theo id
				Product productById = productServiceImpl.findByIdProduct(pro_id);
//				Product productById = productServiceImpl.findByIdProduct(pro_id);
				// tìm detailbill theo idProduct
				Detailbill detailById = detailBillRepository.finDetailbillByIdProduct(pro_id, idBill);
				Detailbill detailbill = null;
				// kiểm tra nếu sản phẩm có tồn tại và số lượng trong kho lớn hơn số lượng cần mua
				if(productById != null && productById.getProQuantity() >= pro_quantity) {
					// kiểm tra detailbill nếu chưa có thì tạo mới 
					if(detailById == null ) {
						detailbill = new Detailbill();
						// set số lượng cho detailbill mới tạo
						detailbill.setQuantity(pro_quantity);
						// thêm product vào detailbill mới
						detailbill.setProduct(productById);
						// tìm bill theo idbill
						Bill bill = billService.findByIdBill(idBill); 
						// add detailbill vừa tạo vào bill
						bill.addDetailbill(detailbill);
						// thêm detailbill
						detailbill = detailBillRepository.save(detailbill);
						return 0;
					} else if(detailById != null) {
						// nếu sản phẩm thêm mới đã có trong detailbill thì chỉ cần tăng số lượng lên
						int newQuantity = detailById.getQuantity() + pro_quantity;
						// thực hiện cập nhật lại số lượng
						detailBillRepository.updateDetailBillNameSake(newQuantity, pro_id, idBill);
						return 0;
					}
					}
				return -1;
	}
	/* tìm tất cả detailbill theo idbill
	 */
	@Override
	public List<Detailbill> findAllByIdBill(Integer idBill) {
		return detailBillRepository.findAllByIdBill(idBill);  
	}
	/* tìm detailbill theo idproduct
	 */
	@Override
	public Detailbill finDetailbillByIdProduct(int pro_id, int idBill) {
		return detailBillRepository.finDetailbillByIdProduct(pro_id, idBill);
	}
	/* cập nhật detailbill tăng số lượng khi sản phẩm đã có trong detailbill
	 */
	@Override
	public int updateDetailBillNameSake(int quantity, int pro_id, int idBill) {
		return detailBillRepository.updateDetailBillNameSake(quantity, pro_id, idBill);
	}

}
