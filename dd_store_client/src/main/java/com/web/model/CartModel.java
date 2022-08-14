package com.web.model;


public class CartModel {
	private ProductModel productmodel;
	private int quantiti;
	
	public CartModel(ProductModel productmodel, int quantiti) {
		super();
		this.productmodel = productmodel;
		this.quantiti = quantiti;
	}
	public CartModel() {
		
	}
	public ProductModel getProductmodel() {
		return productmodel;
	}
	public void setProductmodel(ProductModel productmodel) {
		this.productmodel = productmodel;
	}
	public int getQuantiti() {
		return quantiti;
	}
	public void setQuantiti(int quantiti) {
		this.quantiti = quantiti;
	}
	
	
}
