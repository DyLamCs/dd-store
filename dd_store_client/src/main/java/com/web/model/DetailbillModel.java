package com.web.model;



public class DetailbillModel {

	private int id;
	private int quantity;
	private BillModel bill;
	private ProductModel product;
	public DetailbillModel() {
	}
	
	

	public DetailbillModel(int quantity, BillModel bill, ProductModel product) {
		super();
		this.quantity = quantity;
		this.bill = bill;
		this.product = product;
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

	public BillModel getBill() {
		return this.bill;
	}

	public void setBill(BillModel bill) {
		this.bill = bill;
	}

	public ProductModel getProduct() {
		return this.product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

}