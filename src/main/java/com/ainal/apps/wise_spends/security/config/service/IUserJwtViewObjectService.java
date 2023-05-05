package com.ainal.apps.wise_spends.security.config.service;

import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

public interface IUserJwtViewObjectService {
	UserJwtViewObject loadUserJwtViewObjectByUsernameOrEmail(String usernameOrEmail);
}
