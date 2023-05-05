package com.ainal.apps.wise_spends.manager;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IUserManager {
	Collection<? extends GrantedAuthority> getUserAuthorities(User user);

	Collection<? extends GrantedAuthority> getUserAuthorities(Long userId);
}
