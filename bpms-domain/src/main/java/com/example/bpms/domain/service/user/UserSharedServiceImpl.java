package com.example.bpms.domain.service.user;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.bpms.domain.model.User;
import com.example.bpms.domain.repository.user.UserRepository;

@Service
public class UserSharedServiceImpl implements UserSharedService {

	@Inject
	UserRepository userRepository;

    @Inject
    PasswordEncoder passwordEncoder;
    
	@Transactional(readOnly=true)
	@Override
	public User findOne(String id) {
		
		User user = userRepository.findById(id).orElse(null);
		
		if (user == null) {
			throw new ResourceNotFoundException(id + "というIDは登録されていません");
		}
		
		return user;
	}

	public void create(User user, String password) {
		user.setPassword(passwordEncoder.encode(password));
		userRepository.create(user);
	}
	
	public void update(User user) {
		userRepository.updateById(user);
	}
}
