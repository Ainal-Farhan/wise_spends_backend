package com.ainal.apps.wise_spends.common.repository.ref;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.repository.IBaseEntityRepository;

public interface IUserRoleRepository extends IBaseEntityRepository<UserRole> {
	UserRole findByCode(String code);
}
