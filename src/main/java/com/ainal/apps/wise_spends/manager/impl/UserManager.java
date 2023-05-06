package com.ainal.apps.wise_spends.manager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.domain.usr.Individual;
import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.ref.IUserRoleService;
import com.ainal.apps.wise_spends.common.service.usr.IIndividualService;
import com.ainal.apps.wise_spends.common.service.usr.IRoleService;
import com.ainal.apps.wise_spends.common.service.usr.IUserService;
import com.ainal.apps.wise_spends.manager.IUserManager;

import io.micrometer.common.util.StringUtils;

@Component
public class UserManager implements IUserManager {
	@Autowired
	IUserService userService;

	@Autowired
	IRoleService roleService;

	@Autowired
	IUserRoleService userRoleService;

	@Autowired
	IIndividualService individualService;

	@Override
	public Collection<? extends GrantedAuthority> getUserAuthorities(final User user) {
		if (user == null) {
			return List.of();
		}

		List<GrantedAuthority> gAList = new ArrayList<>();

		List<Role> roles = roleService.findRoleListBasedOnUser(user);

		if (!CollectionUtils.isEmpty(roles)) {
			for (Role role : roles) {
				if (role.getUserRole() != null) {
					UserRole userRole = userRoleService.findUserRoleId(role.getUserRole().getId());
					if (userRole != null && userRole.getFlagActive()) {
						gAList.add(new SimpleGrantedAuthority(userRole.getCode()));
					}
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

	@Override
	public List<User> findUserListByEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return List.of();
		}
		List<Individual> individualList = individualService.findIndividualByEmail(email);
		List<User> userList = individualList.stream().map(individual -> individual.getUser()).toList().stream()
				.filter(user -> user.getFlagActive() != null && user.getFlagActive()).collect(Collectors.toList());
		return userList;
	}

	@Override
	public Individual findByUser(User user) {
		return individualService.findIndividualByUser(user);
	}

}
