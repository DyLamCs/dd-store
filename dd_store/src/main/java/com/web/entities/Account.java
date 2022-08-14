package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the accounts database table.
 * 
 */
@Entity
@Table(name="accounts")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="acc_id")
	private int accId;

	@Column(name="acc_city")
	private String accCity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="acc_datecrate")
	private Date accDatecrate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="acc_datemodify")
	private Date accDatemodify;

	@Column(name="acc_deleted")
	private byte accDeleted;

	@Column(name="acc_email")
	private String accEmail;

	@Column(name="acc_enabled")
	private boolean accEnabled;

	@Column(name="acc_name")
	private String accName;

	@Column(name="acc_password")
	private String accPassword;

	@Column(name="acc_phone")
	private int accPhone;

	@Column(name="acc_role")
	private String accRole;

	@Column(name="acc_street")
	private String accStreet;

	//bi-directional many-to-one association to Bill
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Bill> bills;

	public Account() {
	}

	public int getAccId() {
		return this.accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getAccCity() {
		return this.accCity;
	}

	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	public Date getAccDatecrate() {
		return this.accDatecrate;
	}

	public void setAccDatecrate(Date accDatecrate) {
		this.accDatecrate = accDatecrate;
	}

	public Date getAccDatemodify() {
		return this.accDatemodify;
	}

	public void setAccDatemodify(Date accDatemodify) {
		this.accDatemodify = accDatemodify;
	}

	public byte getAccDeleted() {
		return this.accDeleted;
	}

	public void setAccDeleted(byte accDeleted) {
		this.accDeleted = accDeleted;
	}

	public String getAccEmail() {
		return this.accEmail;
	}

	public void setAccEmail(String accEmail) {
		this.accEmail = accEmail;
	}

	public boolean getAccEnabled() {
		return this.accEnabled;
	}

	public void setAccEnabled(boolean b) {
		this.accEnabled = b;
	}

	public String getAccName() {
		return this.accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccPassword() {
		return this.accPassword;
	}

	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}

	public int getAccPhone() {
		return this.accPhone;
	}

	public void setAccPhone(int accPhone) {
		this.accPhone = accPhone;
	}

	public String getAccRole() {
		return this.accRole;
	}

	public void setAccRole(String accRole) {
		this.accRole = accRole;
	}

	public String getAccStreet() {
		return this.accStreet;
	}

	public void setAccStreet(String accStreet) {
		this.accStreet = accStreet;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setAccount(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setAccount(null);

		return bill;
	}

}