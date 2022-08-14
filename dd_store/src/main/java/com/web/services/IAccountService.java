package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Account;


public interface IAccountService{
	List<Account> findAll();
	Optional<Account> findById(Integer id);
	Optional<Account> findByAccName(String accName);
	Account create(Account account);
	Account update(Account accountDetail, Integer Id);
	Account delete (Integer id);
	Account signup(Account account);
	
}
