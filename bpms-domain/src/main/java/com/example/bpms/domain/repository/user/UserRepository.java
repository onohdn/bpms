package com.example.bpms.domain.repository.user;

import java.util.Collection;
import java.util.Optional;

import com.example.bpms.domain.model.User;

public interface UserRepository {

	Optional<User> findById(String id);
	
	Collection<User> findAll();
	
	void create(User user);
	
	boolean updateById(User user);
	
	void deleteById(User user);
	
}
