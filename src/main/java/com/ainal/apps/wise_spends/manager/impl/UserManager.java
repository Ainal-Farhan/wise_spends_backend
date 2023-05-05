package com.ainal.apps.wise_spends.manager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.usr.IRoleService;
import com.ainal.apps.wise_spends.common.service.usr.IUserService;
import com.ainal.apps.wise_spends.manager.IUserManager;

@Service
public class UserManager implements IUserManager {
	@Autowired
	IUserService userService;

	@Autowired
	IRoleService roleService;

	@Override
	public Collection<? extends GrantedAuthority> getUserAuthorities(final User user) {
		if (user == null) {
			return List.of();
		}
		
		List<GrantedAuthority> gAList = new ArrayList<>();

		List<Role> roles = roleService.findRoleListBasedOnUser(user);

		if (!CollectionUtils.isEmpty(roles)) {
			for (Role role : roles) {
				if (role.getUserRole() != null && role.getUserRole().getFlagActive()) {
					gAList.add(new SimpleGrantedAuthority(role.getUserRole().getCode()));
				}
			}

		}

		return gAList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getUserAuthorities(Long userId) {
		if (userId == null) {
			return List.of();
		}
		return getUserAuthorities(userService.findByUserId(userId));
	}

}
