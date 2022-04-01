package com.example.domain;

/**
 * 
 * @author hondayuki
 *
 */
public class Category {
    /** ID */
    private Integer id;
    /** 親ID */
    private Integer parentId;
    /** カテゴリ名 */
    private String name;
    /** 連結カテゴリ名 */
    private String nameAll;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNameAll() {
        return nameAll;
    }
    public void setNameAll(String nameAll) {
        this.nameAll = nameAll;
    }
    
    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", nameAll=" + nameAll + ", parentId=" + parentId + "]";
    }
    
}
