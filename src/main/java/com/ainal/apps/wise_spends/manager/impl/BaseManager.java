package com.ainal.apps.wise_spends.manager.impl;

import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.security.config.service.IJwtService;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BaseManager implements IBaseManager {
	private final IJwtService jwtService;

	@Override
	public void setBaseEntityAttributes(@NonNull BaseEntity base, @NonNull HttpServletRequest request) {
		setBaseEntityAttributes(base, getCurrentUser(request));
	}

	@Override
	public void setBaseEntityAttributes(@NonNull BaseEntity base, @NonNull String name) {
		Date currentDate = new Date();
		name = StringUtils.isBlank(name) ? "SYSTEM" : name;

		if (StringUtils.isBlank(base.getCreatedBy())) {
			base.setCreatedBy(name);
			base.setCreatedDate(currentDate);
		}

		base.setLastModifiedBy(name);
		base.setLastModifiedDate(currentDate);
	}

	private String getCurrentUser(@NonNull HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
		final String startWith = "Bearer ";
		if (StringUtils.isBlank(authHeader) || authHeader.startsWith(startWith)) {
			return Strings.EMPTY;
		}

		final String jwt = authHeader.substring(startWith.length());

		String usernameOrEmail = jwtService.extractUsernameOrEmail(jwt);
		return StringUtils.isBlank(usernameOrEmail) ? Strings.EMPTY : usernameOrEmail;
	}
}
