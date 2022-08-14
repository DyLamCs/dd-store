package com.web.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.entities.Account;
import com.web.repositories.AccountRepository;
@Service
public class MyUserDetailServices implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	/** 
	 * Lấy thông tin của người đăng nhập thông qua username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> accountOptional = accountRepository.findByaccName(username);
		if (accountOptional.isEmpty()) {
			throw new UsernameNotFoundException("Not found username");
			
		}
		return new User(accountOptional.get().getAccName(), accountOptional.get().getAccPassword(),
				accountOptional.get().getAccEnabled(), true, true, true,
				getGrantedAuthorities(accountOptional.get().getAccRole()));
	}
	
	/**
     * kiem tra role
     * @param rolename
     * @return authorities
     */
    private List<GrantedAuthority> getGrantedAuthorities(String rolename) {
        List<GrantedAuthority> authorities = new ArrayList<>();        
            authorities.add(new SimpleGrantedAuthority(rolename));        
        return authorities;
    }

}
