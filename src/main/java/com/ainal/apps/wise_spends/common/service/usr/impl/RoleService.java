package com.ainal.apps.wise_spends.common.service.usr.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.usr.IRoleRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.usr.IRoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService extends BaseService implements IRoleService {
	@Autowired
	IRoleRepository roleRepository;

	@Override
	public List<Role> findRoleListBasedOnUser(User user) {
		return roleRepository.findByUser(user);
	}

}
