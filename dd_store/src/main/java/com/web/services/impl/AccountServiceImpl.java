package com.web.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.entities.Account;
import com.web.repositories.AccountRepository;
import com.web.services.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	/**
	 * Tìm tất cả đối tượng trong DB
	 */
	@Override
	public List<Account> findAll() {
		return accountRepository.findAllByaccDeleted((byte)0);
				
	}

	/**
	 * Tìm một đối tượng thông qua Id trong DB
	 */
	@Override
	public Optional<Account> findById(Integer id) {
		return accountRepository.findById(id);
	}
	
	@Override
	public Optional<Account> findByAccName(String accName) {
		return accountRepository.findByAccNameAndAccDeleted(accName,(byte) 0);
	}

	/**
	 * Thêm mới một đối tượn vào trong DB
	 */
	@Override
	public Account create(Account account) {
			
		account.setAccDatecrate(new Date());
		account.setAccDatemodify(new Date());
		account.setAccPassword(passwordEncoder.encode(account.getAccPassword()));
		account.setAccEnabled(true);
		return accountRepository.save(account);
	}

	/**
	 * Chỉnh sửa thông tin của một đối tượng trong DB
	 */
	@Override
	public Account update(Account accountDetails, Integer Id ) {
		Account account = accountRepository.findById(Id).get();
		
		account.setAccName(accountDetails.getAccName());
		account.setAccPhone(accountDetails.getAccPhone());
		account.setAccEmail(accountDetails.getAccEmail());
		account.setAccStreet(accountDetails.getAccStreet());
		account.setAccCity(accountDetails.getAccCity());
		account.setAccRole(accountDetails.getAccRole());
		account.setAccDatemodify(new Date());
//		account.setAccPassword(passwordEncoder.encode(accountDetails.getAccPassword()));
		account.setAccPassword(account.getAccPassword());  // lấy mk hiện tại
		
		return accountRepository.save(account);
		
	}
	

	/**
	 * Xóa một đối tượng ra khỏi DB thông qua Id
	 */
	@Override
	public Account delete(Integer id) {
		Account account = accountRepository.findById(id).get();
		
		account.setAccDeleted((byte)1);
		
		return accountRepository.save(account);
	}

	/**
	 * Tạo một tài khoản với ROLE_USER
	 */
	@Override
	public Account signup(Account account) {
		account.setAccDatecrate(new Date());
		account.setAccDatemodify(new Date());
		account.setAccPassword(passwordEncoder.encode(account.getAccPassword()));
		account.setAccEnabled(true);
		account.setAccRole("ROLE_USER");
		
		return accountRepository.save(account);
	}

	

	




	
	
	
}
