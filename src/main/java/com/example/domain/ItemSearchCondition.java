package com.example.domain;
/**
 * 
 * @author hondayuki
 *
 */
public class ItemSearchCondition {
    /** アイテム名 */
    private String name;
    /** カテゴリ（小カテゴリID） */
    private Integer category;
    /** ブランド */
    private String brand;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ItemSearchCondition [brand=" + brand + ", category=" + category + ", name=" + name + "]";
    }
}
