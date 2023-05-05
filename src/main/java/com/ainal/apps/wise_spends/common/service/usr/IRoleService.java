package com.ainal.apps.wise_spends.common.service.usr;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IRoleService {
	List<Role> findRoleListBasedOnUser(User user);
}
