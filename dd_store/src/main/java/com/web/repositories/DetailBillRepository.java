package com.web.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.web.entities.Detailbill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DetailBillRepository extends JpaRepository<Detailbill, Integer>{
	/**
	 * thêm thông tin bill vào detailbill
	 */
	@Query(value = "INSERT INTO detailbill (pro_id, bill_id, quantity) VALUES (?1, ?2, ?3)", nativeQuery = true)
	public Detailbill insertByIdBill(int idBill, int pro_id, int quantity);
	
	/**
	 * tìm detailbill theo id_product
	 */
	@Query(value = "SELECT * FROM detailbill d WHERE d.pro_id=?1 AND d.bill_id=?2", nativeQuery = true)
	public Detailbill finDetailbillByIdProduct(int pro_id, int idBill);
	
	/**
	 * cập nhật detailbill tăng số lượng lên khi sản phẩm trùng theo idproduct
	 */
	@Modifying
	@Query(value = "UPDATE detailbill d SET d.quantity=?1 WHERE d.pro_id=?2 AND d.bill_id=?3", nativeQuery = true)
	public int updateDetailBillNameSake(int quantity,int pro_id, int idBill);
	
	/**
	 * Xóa product khỏi detailbill
	 */
	@Modifying
	@Query(value = "DELETE FROM detailbill d WHERE d.pro_id=?1", nativeQuery = true)
	public int deleteProductFromDetailBillByIdProduct(int pro_id);
	
	
	/**
	 * tìm tất cả detailbill theo idBill
	 */
	@Query(value = "SELECT * FROM detailbill d WHERE d.bill_id=?1", nativeQuery = true)
	public List<Detailbill> findAllByIdBill(Integer pro_id); 
}
