package com.ainal.apps.wise_spends.common.service.ref.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.repository.ref.IUserRoleRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.ref.IUserRoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRoleService extends BaseService implements IUserRoleService {
	@Autowired
	IUserRoleRepository userRoleRepository;
	
	@Override
	public List<UserRole> findUserRoleListByRoleList(List<Role> roleList) {
		return userRoleRepository.findByRoleList(roleList);
	}

}
