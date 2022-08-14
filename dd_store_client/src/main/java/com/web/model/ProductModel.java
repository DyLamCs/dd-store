package com.web.model;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
public class ProductModel {

	private int proId;
	private String proCode;
	private Date proDatecrate;
	private Date proDatemodify;
	private byte proDeleted;
	private String proDetail;
	private String proImage;
	
	private String proName;
	private String proPrice;
	private int proQuantity;

	private List<DetailbillModel> detailbills;
	private BrandModel brand = new BrandModel();
	private CategoryModel category = new CategoryModel();

	public ProductModel() {
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public Date getProDatecrate() {
		return proDatecrate;
	}

	public void setProDatecrate(Date proDatecrate) {
		this.proDatecrate = proDatecrate;
	}

	public Date getProDatemodify() {
		return proDatemodify;
	}

	public void setProDatemodify(Date proDatemodify) {
		this.proDatemodify = proDatemodify;
	}

	public byte getProDeleted() {
		return proDeleted;
	}

	public void setProDeleted(byte proDeleted) {
		this.proDeleted = proDeleted;
	}

	public String getProDetail() {
		return proDetail;
	}

	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}

	public String getProImage() {
		return proImage;
	}

	public void setProImage(String proImage) {
		this.proImage = proImage;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProPrice() {
		return proPrice;
	}

	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}

	public int getProQuantity() {
		return proQuantity;
	}

	public void setProQuantity(int proQuantity) {
		this.proQuantity = proQuantity;
	}

	public List<DetailbillModel> getDetailbills() {
		return detailbills;
	}

	public void setDetailbills(List<DetailbillModel> detailbills) {
		this.detailbills = detailbills;
	}

	public BrandModel getBrand() {
		return brand;
	}

	public void setBrand(BrandModel brand) {
		this.brand = brand;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	

}