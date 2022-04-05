package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @author hondayuki
 *
 */
public class UserInsertForm {
	/** 名前（email） */
    @NotBlank
    @Email
	private String name;
	/** パスワード */
    @NotBlank
    @Size(min = 8, max = 16)
	private String password;

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

    @Override
    public String toString() {
        return "UserInsertForm [name=" + name + ", password=" + password + "]";
    }

}
