package com.ainal.apps.wise_spends.common.service.ref.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.repository.ref.IUserRoleRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.ref.IUserRoleService;
import com.ainal.apps.wise_spends.manager.IBaseManager;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRoleService extends BaseService implements IUserRoleService {
	@Autowired
	IUserRoleRepository userRoleRepository;

	@Autowired
	private IBaseManager baseManager;

	@Override
	public UserRole findUserRoleId(Long userRoleId) {
		if (userRoleId == null) {
			return null;
		}

		return userRoleRepository.findById(userRoleId).orElse(null);
	}

	@Override
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}

	@Override
	public void save(UserRole userRole) {
		baseManager.setBaseEntityAttributes(userRole, "System");
		userRoleRepository.saveAndFlush(userRole);
	}

}
