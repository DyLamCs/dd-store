package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pro_id")
	private int proId;

	@Column(name="pro_code")
	private String proCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pro_datecrate")
	private Date proDatecrate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pro_datemodify")
	private Date proDatemodify;

	@Column(name="pro_deleted")
	private byte proDeleted;

	@Lob
	@Column(name="pro_detail")
	private String proDetail;

	@Column(name="pro_image")
	private String proImage;

	

	@Column(name="pro_name")
	private String proName;

	@Lob
	@Column(name="pro_price")
	private String proPrice;

	@Column(name="pro_quantity")
	private int proQuantity;

	//bi-directional many-to-one association to Detailbill
	@OneToMany(mappedBy="product")
	private List<Detailbill> detailbills;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="categories_id")
	private Category category;

	public Product() {
	}

	public int getProId() {
		return this.proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProCode() {
		return this.proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public Date getProDatecrate() {
		return this.proDatecrate;
	}

	public void setProDatecrate(Date proDatecrate) {
		this.proDatecrate = proDatecrate;
	}

	public Date getProDatemodify() {
		return this.proDatemodify;
	}

	public void setProDatemodify(Date proDatemodify) {
		this.proDatemodify = proDatemodify;
	}

	public byte getProDeleted() {
		return this.proDeleted;
	}

	public void setProDeleted(byte proDeleted) {
		this.proDeleted = proDeleted;
	}

	public String getProDetail() {
		return this.proDetail;
	}

	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}

	public String getProImage() {
		return this.proImage;
	}

	public void setProImage(String proImage) {
		this.proImage = proImage;
	}

	

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProPrice() {
		return this.proPrice;
	}

	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}

	public int getProQuantity() {
		return this.proQuantity;
	}

	public void setProQuantity(int proQuantity) {
		this.proQuantity = proQuantity;
	}

	public List<Detailbill> getDetailbills() {
		return this.detailbills;
	}

	public void setDetailbills(List<Detailbill> detailbills) {
		this.detailbills = detailbills;
	}

	public Detailbill addDetailbill(Detailbill detailbill) {
		getDetailbills().add(detailbill);
		detailbill.setProduct(this);

		return detailbill;
	}

	public Detailbill removeDetailbill(Detailbill detailbill) {
		getDetailbills().remove(detailbill);
		detailbill.setProduct(null);

		return detailbill;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}