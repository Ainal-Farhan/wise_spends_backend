package com.ainal.apps.wise_spends.security.config.service;

import java.util.Optional;

import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

public interface IUserJwtViewObjectService {
	Optional<UserJwtViewObject>  loadUserJwtViewObjectByUsernameOrEmail(String usernameOrEmail);
}
