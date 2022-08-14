package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name="brands")
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bra_id")
	private int braId;

	@Column(name="bra_name")
	private String braName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="brand")
	@JsonIgnore
	private List<Product> products;

	public Brand() {
	}

	public int getBraId() {
		return this.braId;
	}

	public void setBraId(int braId) {
		this.braId = braId;
	}

	public String getBraName() {
		return this.braName;
	}

	public void setBraName(String braName) {
		this.braName = braName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setBrand(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setBrand(null);

		return product;
	}

}