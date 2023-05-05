package com.ainal.apps.wise_spends.common.repository.usr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IRoleRepository extends JpaRepository<Role, Long>{

	List<Role> findByUser(User user);

}
