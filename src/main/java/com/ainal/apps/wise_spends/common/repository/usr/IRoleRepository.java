package com.ainal.apps.wise_spends.common.repository.usr;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.IBaseEntityRepository;

public interface IRoleRepository extends IBaseEntityRepository<Role>{

	List<Role> findByUser(User user);

}
