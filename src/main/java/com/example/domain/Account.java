package com.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author hondayuki
 *
 */
public class Account extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
    private final User user;

    public Account(User user, Collection<GrantedAuthority> authorityList) {
        super(user.getName(), user.getPassword(), authorityList);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
