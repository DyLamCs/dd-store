package com.web.model;



import java.util.List;


/**
 * The persistent class for the brands database table.
 * 
 */
public class BrandModel {
	
	private int braId;
	private String braName;
	
	private List<ProductModel> products;

	public BrandModel() {
	}
	
	

	public BrandModel(int braId, String braName, List<ProductModel> products) {
	
		this.braId = braId;
		this.braName = braName;
		this.products = products;
	}



	public int getBraId() {
		return braId;
	}

	public void setBraId(int braId) {
		this.braId = braId;
	}

	public String getBraName() {
		return braName;
	}

	public void setBraName(String braName) {
		this.braName = braName;
	}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}
	
	

	

}