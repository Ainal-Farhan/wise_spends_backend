package com.ainal.apps.wise_spends.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class BaseManager implements IBaseManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Override
	public void setBaseEntityAttributes(@NonNull BaseEntity base, @NonNull HttpServletRequest request) {
		setBaseEntityAttributes(base, currentUserManager.getCurrentUserEmailOrUsername(request));
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

	@Override
	public void setBaseEntityAttributes(BaseEntity base, User currentUser) {
		setBaseEntityAttributes(base, currentUserManager.getCurrentUserEmailOrUsername(currentUser));
	}
}
