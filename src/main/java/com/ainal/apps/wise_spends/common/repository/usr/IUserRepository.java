package com.ainal.apps.wise_spends.common.repository.usr;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.BaseEntityRepository;

public interface IUserRepository extends BaseEntityRepository<User> {
	@Query("SELECT u FROM User u WHERE u.credential = (SELECT c FROM Credential c WHERE c.username = :username)")
	List<User> findUserByUsername(@Param("username") String username);
}
