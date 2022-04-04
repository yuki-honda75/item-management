package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author hondayuki
 *
 */
public class ItemUpdateForm {
	/** id */
	private Integer id;
	/** アイテム名 */
    @NotBlank
	private String name;
	/** 状態 */
    @NotEmpty
	private String condition;
	/** 小カテゴリ */
    @NotBlank
	private String sCategory;
	/** ブランド */
    @NotBlank
	private String brand;
	/** 価格 */
    @NotBlank
	private String price;
	/** 説明 */
    @NotBlank
	private String description;
    
    /**
     * コンディションの型変換
     * @return Integer型
     */
    public Integer getIntCondition() {
        return Integer.parseInt(condition);
    }

    /**
     * 小カテゴリの型変換
     * @return Integer型
     */
    public Integer getIntSCategory() {
        return Integer.parseInt(sCategory);
    }

    /**
     * 価格の型変換
     * @return Integer型
     */
    public Double getDoublePrice() {
        return Double.parseDouble(price);
    }

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
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getsCategory() {
        return sCategory;
    }
    public void setsCategory(String sCategory) {
        this.sCategory = sCategory;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemInsertForm [brand=" + brand + ", condition=" + condition + ", description=" + description + ", id="
                + id + ", name=" + name + ", price=" + price
                + ", sCategory=" + sCategory + "]";
    }
    
}
