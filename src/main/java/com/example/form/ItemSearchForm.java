package com.example.form;
/**
 * 
 * @author hondayuki
 *
 */
public class ItemSearchForm {
    /** アイテム名 */
    private String name;
    /** カテゴリ（小カテゴリID） */
    private String category;
    /** ブランド */
    private String brand;

    public Integer getIntCategory() throws NumberFormatException {
        return Integer.parseInt(category);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
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
