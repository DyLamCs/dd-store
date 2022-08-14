package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the detailbill database table.
 * 
 */
@Entity
@NamedQuery(name="Detailbill.findAll", query="SELECT d FROM Detailbill d")
public class Detailbill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int quantity;

	//bi-directional many-to-one association to Bill
	@ManyToOne
	private Bill bill;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="pro_id")
	private Product product;

	public Detailbill() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}