package com.example.bpms.domain.repository.user;

import java.util.Collection;

import com.example.bpms.domain.model.User;
import com.google.common.base.Optional;

public interface UserRepository {

	Optional<User> findById(String id);
	
	Collection<User> findAll();
	
	void create(User user);
	
	boolean updateById(User user);
	
	void deleteById(User user);
	
}
