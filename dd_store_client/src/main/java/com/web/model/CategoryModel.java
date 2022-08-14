package com.web.model;



import java.util.List;


public class CategoryModel {
	private int ctgId;
	private String ctgName;
	private List<ProductModel> products;
	
	public CategoryModel() {
	}
	
	

	public CategoryModel(int ctgId, String ctgName, List<ProductModel> products) {

		this.ctgId = ctgId;
		this.ctgName = ctgName;
		this.products = products;
	}


	public int getCtgId() {
		return this.ctgId;
	}

	public void setCtgId(int ctgId) {
		this.ctgId = ctgId;
	}

	public String getCtgName() {
		return this.ctgName;
	}

	public void setCtgName(String ctgName) {
		this.ctgName = ctgName;
	}

	public List<ProductModel> getProducts() {
		return this.products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}

	public ProductModel addProduct(ProductModel product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public ProductModel removeProduct(ProductModel product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}