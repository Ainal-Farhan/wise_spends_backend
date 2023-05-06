package com.ainal.apps.wise_spends.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.service.ref.IUserRoleService;
import com.ainal.apps.wise_spends.manager.IReferenceManager;

@Component
public class ReferenceManager implements IReferenceManager {
	@Autowired
	IUserRoleService userRoleService;

	@Override
	public void resetUserRoleRef() {
		List<UserRole> userRolesList = userRoleService.findAll();
		if (!userRolesList.stream().anyMatch(userRole -> "N_USR".equals(userRole.getCode()))) {
			UserRole userRole = new UserRole();
			userRole.setCode("N_USR");
			userRole.setName("Normal User");
			userRoleService.save(userRole);
		}

		if (!userRolesList.stream().anyMatch(userRole -> "R_ADM".equals(userRole.getCode()))) {
			UserRole userRole = new UserRole();
			userRole.setCode("R_ADM");
			userRole.setName("Admin");
			userRoleService.save(userRole);
		}
	}

}
