package com.ainal.apps.wise_spends.common.repository.usr;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IUserRepository extends JpaRepository<User, Long>{

}
