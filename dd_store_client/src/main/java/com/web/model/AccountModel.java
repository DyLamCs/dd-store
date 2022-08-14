package com.web.model;



import java.util.Date;
import java.util.List;


/**
 * The persistent class for the accounts database table.
 * 
 */

public class AccountModel {
	
	private int accId;
	private String accName;
	private int accPhone;
	private String accEmail;
	private String accStreet;
	private String accCity;
	private String accPassword;
	private String accRole;
	private Date accDatecrate;
	private Date accDatemodify;
	private byte accDeleted;
	private boolean accEnabled;

	private List<BillModel> bills;

	public AccountModel() {
	}

	public AccountModel(int accId, String accName, int accPhone, String accEmail, String accStreet, String accCity,
			String accPassword, String accRole, Date accDatecrate, Date accDatemodify, byte accDeleted,
			boolean accEnabled, List<BillModel> bills) {
		
		this.accId = accId;
		this.accName = accName;
		this.accPhone = accPhone;
		this.accEmail = accEmail;
		this.accStreet = accStreet;
		this.accCity = accCity;
		this.accPassword = accPassword;
		this.accRole = accRole;
		this.accDatecrate = accDatecrate;
		this.accDatemodify = accDatemodify;
		this.accDeleted = accDeleted;
		this.accEnabled = accEnabled;
		this.bills = bills;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public int getAccPhone() {
		return accPhone;
	}

	public void setAccPhone(int accPhone) {
		this.accPhone = accPhone;
	}

	public String getAccEmail() {
		return accEmail;
	}

	public void setAccEmail(String accEmail) {
		this.accEmail = accEmail;
	}

	public String getAccStreet() {
		return accStreet;
	}

	public void setAccStreet(String accStreet) {
		this.accStreet = accStreet;
	}

	public String getAccCity() {
		return accCity;
	}

	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	public String getAccPassword() {
		return accPassword;
	}

	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}

	public String getAccRole() {
		return accRole;
	}

	public void setAccRole(String accRole) {
		this.accRole = accRole;
	}

	public Date getAccDatecrate() {
		return accDatecrate;
	}

	public void setAccDatecrate(Date accDatecrate) {
		this.accDatecrate = accDatecrate;
	}

	public Date getAccDatemodify() {
		return accDatemodify;
	}

	public void setAccDatemodify(Date accDatemodify) {
		this.accDatemodify = accDatemodify;
	}

	public byte getAccDeleted() {
		return accDeleted;
	}

	public void setAccDeleted(byte accDeleted) {
		this.accDeleted = accDeleted;
	}

	public boolean isAccEnabled() {
		return accEnabled;
	}

	public void setAccEnabled(boolean accEnabled) {
		this.accEnabled = accEnabled;
	}

	
	
	
	
	

	

	

}