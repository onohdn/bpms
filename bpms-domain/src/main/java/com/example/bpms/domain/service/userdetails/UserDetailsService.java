package com.example.bpms.domain.service.userdetails;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.bpms.domain.model.User;
import com.example.bpms.domain.service.user.UserSharedService;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Inject
	UserSharedService userSharedService;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		try {
			User user = userSharedService.findOne(id);
			return new com.example.bpms.domain.service.userdetails.UserDetails(user);
		} catch (ResourceNotFoundException e) {
			throw new UsernameNotFoundException("ユーザが見つかりませんでした", e);
		}
	}

}
