package com.web.model;

import java.util.List;


/**
 * The persistent class for the bill database table.
 * 
 */
public class BillModel{
	private int id;
	private int dateBill;
	private int deleted;
	private double total;
	private AccountModel account;
	private List<DetailbillModel> detailbills;

	public BillModel() {
	}
	
	

	public BillModel(int dateBill, int deleted, double total, AccountModel account, List<DetailbillModel> detailbills) {
		this.dateBill = dateBill;
		this.deleted = deleted;
		this.total = total;
		this.account = account;
		this.detailbills = detailbills;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDateBill() {
		return this.dateBill;
	}

	public void setDateBill(int dateBill) {
		this.dateBill = dateBill;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public AccountModel getAccount() {
		return this.account;
	}

	public void setAccount(AccountModel account) {
		this.account = account;
	}

	public List<DetailbillModel> getDetailbills() {
		return this.detailbills;
	}

	public void setDetailbills(List<DetailbillModel> detailbills) {
		this.detailbills = detailbills;
	}

	public DetailbillModel addDetailbill(DetailbillModel detailbill) {
		getDetailbills().add(detailbill);
		detailbill.setBill(this);

		return detailbill;
	}

	public DetailbillModel removeDetailbill(DetailbillModel detailbill) {
		getDetailbills().remove(detailbill);
		detailbill.setBill(null);

		return detailbill;
	}

}