package com.ainal.apps.wise_spends.common.service.ref.impl;

import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.ref.IUserRoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRoleService extends BaseService implements IUserRoleService {

}
