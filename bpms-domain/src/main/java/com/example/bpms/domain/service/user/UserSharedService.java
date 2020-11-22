package com.example.bpms.domain.service.user;

import com.example.bpms.domain.model.User;

public interface UserSharedService {

	User findOne(String id);
	
	void create(User user, String password);
}
