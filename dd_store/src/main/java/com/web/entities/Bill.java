package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="date_bill")
	private int dateBill;

	private int deleted;

	private double total;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="acc_id")
	private Account account;

	//bi-directional many-to-one association to Detailbill
	@OneToMany(mappedBy="bill")
	private List<Detailbill> detailbills;

	public Bill() {
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

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Detailbill> getDetailbills() {
		return this.detailbills;
	}

	public void setDetailbills(List<Detailbill> detailbills) {
		this.detailbills = detailbills;
	}

	public Detailbill addDetailbill(Detailbill detailbill) {
		getDetailbills().add(detailbill);
		detailbill.setBill(this);

		return detailbill;
	}

	public Detailbill removeDetailbill(Detailbill detailbill) {
		getDetailbills().remove(detailbill);
		detailbill.setBill(null);

		return detailbill;
	}

}