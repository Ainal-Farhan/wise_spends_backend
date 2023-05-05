package com.ainal.apps.wise_spends.common.service.usr.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.usr.IUserRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.usr.IUserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService extends BaseService implements IUserService {
	@Autowired
	IUserRepository userRepository;
	
	@Override
	public User findByUserId(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
