package com.example.bpms.domain.service.userdetails;

import org.springframework.security.core.authority.AuthorityUtils;

import com.example.bpms.domain.model.User;

public class UserDetails extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	public UserDetails(User user) {
		super (user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
