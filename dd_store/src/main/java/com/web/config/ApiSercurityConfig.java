package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.web.services.impl.MyUserDetailServices;



/**
 * Cấu hình Security phía API
 * 
 * class thua ke WebSecurityConfigurerAdapter
 *
 */
@Configuration
@EnableWebSecurity
public class ApiSercurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private MyUserDetailServices myUserDetailServices;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		 http.cors().and().csrf().disable().httpBasic();
		// tất những cái mặc định 
		http.csrf().disable()
		.authorizeRequests().antMatchers("/api/**").permitAll()
		// Tạo tài khoản dưới vai trò người dùng chưa có tài khoản.
		.antMatchers(HttpMethod.POST,"/api/create-user/**").permitAll()
		// Phân quyển cho phía admin
		.antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
		// tắt formlogin mặc định
		.and().formLogin().disable().httpBasic();
	}
	
	/**
	 * Ma hoa mat khau
	 */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailServices).passwordEncoder(passwordEncoder());  
    }
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
	
}