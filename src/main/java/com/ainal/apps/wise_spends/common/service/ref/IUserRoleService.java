package com.ainal.apps.wise_spends.common.service.ref;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.domain.usr.Role;

public interface IUserRoleService {
	List<UserRole> findUserRoleListByRoleList(List<Role> roleList);
}
