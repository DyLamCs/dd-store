package com.web.services;

import java.util.List;

import javax.servlet.http.Cookie;

import com.web.model.AccountModel;
import com.web.model.ResponseObjectModel;

public interface IAccountService {
	/**
	 * Gọi api lấy list các đối tượng
	 * 
	 * @param url đến API
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<List<AccountModel>> getAccount(String url);

	/**
	 * Gọi api lấy một đối tượng theo id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần lấy
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<AccountModel> getOneAccount(String url, int id);

	/**
	 * Gọi api Thêm đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần thêm
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<AccountModel> createAccount(String url, AccountModel accountModel);
	
	/**
	 * Gọi api đăng ký tài khoản mới cho người dùng chưa có tài khoản
	 * @param url
	 * @param accountModel
	 * @return
	 */
	public ResponseObjectModel<AccountModel> signup(String url, AccountModel accountModel);

	/**
	 * Gọi api Sửa đối tượng với các thuộc tính được thay đổi
	 * 
	 * @param url          đến API
	 * @param studentModel Đối tượng chứa các thuộc tính cần sửa, bao gồm ID
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<AccountModel> updateAccount(String url, AccountModel accountModel);

	/**
	 * Gọi api Xóa đối tượng với id
	 * 
	 * @param url đến API
	 * @param id  đối tượng cần xóa
	 * @return ResponseObject chứa thông tin truy xuất
	 */
	public ResponseObjectModel<AccountModel> deleteAccount(String url, int accId);

	String checkCookie(String cookieName, Cookie[] cookies);

	boolean setCookie(String cookie);

	ResponseObjectModel<AccountModel> getProfile(String cookie);

	ResponseObjectModel<AccountModel> login(AccountModel accountModel);

}
