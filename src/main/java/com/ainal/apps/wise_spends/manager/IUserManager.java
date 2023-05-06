package com.ainal.apps.wise_spends.manager;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.ainal.apps.wise_spends.common.domain.usr.Individual;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IUserManager {
	Collection<? extends GrantedAuthority> getUserAuthorities(User user);

	Collection<? extends GrantedAuthority> getUserAuthorities(Long userId);
	
	List<User> findUserListByEmail(String email);
	
	Individual findByUser(User user);
}
