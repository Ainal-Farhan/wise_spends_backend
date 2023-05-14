package com.ainal.apps.wise_spends.manager;

import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ICurrentUserManager {
	User getCurrentUser(@NonNull HttpServletRequest request);

	String retrieveToken(@NonNull HttpServletRequest request);

	void removeToken(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response);

	void setToken(@NonNull HttpServletResponse response, @NonNull String token);

	UserJwtViewObject getUserJwtViewObject(@NonNull HttpServletRequest request);

	String getCurrentUserEmailOrUsername(User currentUser);

	String getCurrentUserEmailOrUsername(@NonNull HttpServletRequest request);
}
