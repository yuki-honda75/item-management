package com.example.domain;

/**
 * 
 * @author hondayuki
 *
 */

public class Item {
	/** id */
	private Integer id;
	/** アイテム名 */
	private String name;
	/** 状態 */
	private Integer condition;
	/** カテゴリ */
	private Integer category;
	/** ブランド */
	private String brand;
	/** 価格 */
	private Double price;
	/** 運送 */
	private Integer shipping;
	/** 説明 */
	private String description;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCondition() {
		return condition;
	}


	public void setCondition(Integer condition) {
		this.condition = condition;
	}


	public Integer getCategory() {
		return category;
	}


	public void setCategory(Integer category) {
		this.category = category;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getShipping() {
		return shipping;
	}


	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Item [brand=" + brand + ", category=" + category + ", condition=" + condition + ", description="
				+ description + ", id=" + id + ", name=" + name + ", price=" + price + ", shipping=" + shipping + "]";
	}

	
}
