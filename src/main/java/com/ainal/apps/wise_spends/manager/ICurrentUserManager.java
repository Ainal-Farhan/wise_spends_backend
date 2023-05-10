package com.ainal.apps.wise_spends.manager;

import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface ICurrentUserManager {
	User getCurrentUser(@NonNull HttpServletRequest request);

	String retrieveToken(@NonNull HttpServletRequest request);

	UserJwtViewObject getUserJwtViewObject(@NonNull HttpServletRequest request);
}
