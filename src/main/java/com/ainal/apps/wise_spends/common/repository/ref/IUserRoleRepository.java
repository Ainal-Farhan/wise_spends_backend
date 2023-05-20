package com.ainal.apps.wise_spends.common.repository.ref;

import java.util.Optional;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.repository.IBaseEntityRepository;


public interface IUserRoleRepository extends IBaseEntityRepository<UserRole> {
	Optional<UserRole> findByCode(String code);
}
