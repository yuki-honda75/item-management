package com.example.domain;
/**
 * 
 * @author hondayuki
 *
 */
public class User {
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** パスワード */
	private String password;
	/** 認可ロール */
	private Integer authority;

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [authority=" + authority + ", id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
