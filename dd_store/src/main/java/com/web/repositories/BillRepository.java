package com.web.repositories;

import java.util.List;

import com.web.entities.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BillRepository extends JpaRepository<Bill, Integer> {
	/**
	 * Tìm tất cả bill theo deleted
	 */
	@Query(value = "SELECT * FROM bill b WHERE b.deleted=?1", nativeQuery = true)
	public List<Bill> findAllByBillDeleted(int deleted);
	
	/**
	 * kiểm tra bill đã tồn tại và chưa thanh toán
	 */
	@Query(value = "SELECT * FROM bill b WHERE b.id_account=?1 AND b.deleted=0 ", nativeQuery = true)
	public Bill checkBill(int acc_id);
	
	/**
	 * cập nhật trạng thái deleted=2 (2:xóa, 0:chưa thanh toán, 1:đã thanh toán)
	 */
	@Modifying
	@Query(value = "UPDATE bill b SET b.deleted=2 WHERE b.id=?1", nativeQuery = true)
	public int deleteBill(int idBill);
	
	/**
	 * Cập nhật trạng thái đã thanh toán.
	 */
	@Modifying
	@Query(value = "UPDATE bill b SET b.deleted=1, b.date_bill=NOW(), b.total=?1 WHERE b.id=?2", nativeQuery = true)
	public void payBill(double totalBill, int idBill);
	
	/**
	 * cập nhật idAccount cho bill
	 */
	@Modifying
	@Query(value = "UPDATE bill b SET b.id_account=?1 WHERE b.id=?2", nativeQuery = true)
	public int updateBill(Integer idAccount, Integer idBill);
}
