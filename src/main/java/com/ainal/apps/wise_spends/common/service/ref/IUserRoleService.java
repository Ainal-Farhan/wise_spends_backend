package com.ainal.apps.wise_spends.common.service.ref;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;

public interface IUserRoleService {
	UserRole findUserRoleId(Long userRoleId);

	List<UserRole> findAll();

	void save(UserRole userRole);
}
