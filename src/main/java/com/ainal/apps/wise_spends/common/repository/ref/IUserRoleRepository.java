package com.ainal.apps.wise_spends.common.repository.ref;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.domain.usr.Role;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
	@Query("SELECT ur FROM UserRole ur where ur.roleList in (?1)")
	List<UserRole> findByRoleList(List<Role> roleList);
}
