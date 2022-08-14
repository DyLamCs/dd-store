package com.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/** 
 * Lớp cấu hình bảo mật
 * @author IVS
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigClient extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        // Thêm bộ lọc custom trước bộ lọc của spring
        .addFilterBefore(new SecurityFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests()
        		// Các đường dẫn cho phép tất cả truy cập
        		.antMatchers("/templates/**").permitAll()
        		.antMatchers("/signup**").permitAll()
        		.antMatchers("/account/signup**").permitAll()
                .antMatchers("/login**").permitAll()
                .antMatchers("/productshow/**").permitAll()
                
                .antMatchers("/productdetail**").permitAll()
                .antMatchers("/cart/**").permitAll()
                .antMatchers("/checkout**").permitAll()
                
                .antMatchers(HttpMethod.POST, "/login/post**").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/account").hasRole("ADMIN")
                // Những đường dẫn còn lại bắt buộc phải đã xác thực
                .anyRequest().authenticated()
                .and()
                // page login
                .formLogin().loginPage("/login")
//                .and().rememberMe().rememberMeCookieName("API_JSESSIONID")
                // ghi nhớ người dùng theo cookie
                // khi logout sẽ xóa cookie "API_JSESSIONID" và clear các xác thực
                .and().logout().logoutUrl("/logout").deleteCookies("API_JSESSIONID").invalidateHttpSession(true).logoutSuccessUrl("/productshow")
                // tắt lọc csrf (mượn quyền người dùng để giả mạo yêu cầu) 
                // và frame option header là sameorigin (cho phép các url có cùng nguồn gốc truy cập)
                .and().csrf().disable();
    }
	
}
