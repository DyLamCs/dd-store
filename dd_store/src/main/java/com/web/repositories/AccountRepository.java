package com.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	/** Phương thước tìm ra tất cả các account chưa xóa (xóa luận lí)
	 * @param accDeleted
	 * @return
	 */
	public List<Account> findAllByaccDeleted(byte accDeleted);
	
	/** Phương thức tìm admin thong qua tên
	 * @param acc_name
	 * @return
	 */
	public Optional<Account> findByaccName(String accName);
	
	public Optional<Account> findByAccNameAndAccDeleted(String accName, byte accDeleted);

}
