package com.ainal.apps.wise_spends.common.repository.ref;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
	Optional<UserRole> findByCode(String code);
}
