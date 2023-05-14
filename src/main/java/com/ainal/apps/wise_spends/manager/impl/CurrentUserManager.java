package com.ainal.apps.wise_spends.manager.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IUserManager;
import com.ainal.apps.wise_spends.security.config.service.IJwtService;
import com.ainal.apps.wise_spends.security.config.service.IUserJwtViewObjectService;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;
import com.ainal.apps.wise_spends.util.properties.WiseSpendsPropertiesUtils;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CurrentUserManager implements ICurrentUserManager {
	@Autowired
	private IJwtService jwtService;

	@Autowired
	private WiseSpendsPropertiesUtils wiseSpendsPropertiesUtils;

	@Autowired
	private IUserJwtViewObjectService userJwtViewObjectService;

	@Autowired
	private IUserManager userManager;

	@Override
	public User getCurrentUser(@NonNull HttpServletRequest request) {
		UserJwtViewObject userJwtViewObject = getUserJwtViewObject(request);

		if (userJwtViewObject == null) {
			return null;
		}

		return userJwtViewObject.getFlagUsernameIsEmail()
				? userManager.findUserByEmail(userJwtViewObject.getUsernameOrEmail())
				: userManager.findUserByUsername(userJwtViewObject.getUsernameOrEmail());
	}

	@Override
	public String retrieveToken(HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
		final String startWith = "Bearer ";
		String jwt;

		// Retrieve the token from the cookie
		Cookie[] cookies = request.getCookies();
		String token = Strings.EMPTY;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (wiseSpendsPropertiesUtils.JWT_COOKIE_ACCESS_TOKEN_NAME().equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}

		if (StringUtils.isBlank(token) && (StringUtils.isBlank(authHeader) || !authHeader.startsWith(startWith))) {
			return null;
		}

		jwt = token;
		if (StringUtils.isBlank(token)) {
			jwt = authHeader.substring(startWith.length());
		}

		return jwt;
	}

	@Override
	public UserJwtViewObject getUserJwtViewObject(HttpServletRequest request) {
		String token = retrieveToken(request);

		if (StringUtils.isBlank(token)) {
			return null;
		}

		String usernameOrEmail = jwtService.extractUsernameOrEmail(token);

		if (StringUtils.isBlank(usernameOrEmail)) {
			return null;
		}

		return this.userJwtViewObjectService.loadUserJwtViewObjectByUsernameOrEmail(usernameOrEmail).orElse(null);
	}

	@Override
	public void removeToken(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(wiseSpendsPropertiesUtils.JWT_COOKIE_ACCESS_TOKEN_NAME())) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					cookie.setDomain(wiseSpendsPropertiesUtils.WS_DOMAIN());
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	@Override
	public void setToken(@NonNull HttpServletResponse response, @NonNull String token) {
		Cookie tokenCookie = new Cookie(wiseSpendsPropertiesUtils.JWT_COOKIE_ACCESS_TOKEN_NAME(), token);
		tokenCookie.setHttpOnly(true);
		tokenCookie.setDomain(wiseSpendsPropertiesUtils.WS_DOMAIN());
		tokenCookie.setPath("/");
		response.addCookie(tokenCookie);
	}

	@Override
	public String getCurrentUserEmailOrUsername(User currentUser) {
		return currentUser == null || currentUser.getCredential() == null || currentUser.getIndividual() == null
				? Strings.EMPTY
				: currentUser.getCredential().getFlagUsernameIsEmail() ? currentUser.getIndividual().getEmail()
						: currentUser.getCredential().getUsername();
	}

	@Override
	public String getCurrentUserEmailOrUsername(@NonNull HttpServletRequest request) {
		User currentUser = getCurrentUser(request);
		return getCurrentUserEmailOrUsername(currentUser);
	}

}
