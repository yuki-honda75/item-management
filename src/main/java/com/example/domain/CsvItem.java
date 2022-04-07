
package com.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author hondayuki
 *
 */
@JsonPropertyOrder({"id", "name", "condition", "category", "brand", "price", "shipping", "description"})
public class CsvItem {
    /** id */
	@JsonProperty("id")
	private Integer id;
	/** アイテム名 */
	@JsonProperty("name")
	private String name;
	/** 状態 */
	@JsonProperty("condition")
	private Integer condition;
	/** 小カテゴリID */
	@JsonProperty("category")
	private Integer sCategoryId;
	/** ブランド */
	@JsonProperty("brand")
	private String brand;
	/** 価格 */
	@JsonProperty("price")
	private Double price;
	/** 運送 */
	@JsonProperty("shipping")
	private Integer shipping;
	/** 説明 */
	@JsonProperty("description")
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
    public Integer getsCategoryId() {
        return sCategoryId;
    }
    public void setsCategoryId(Integer sCategoryId) {
        this.sCategoryId = sCategoryId;
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
    
}
