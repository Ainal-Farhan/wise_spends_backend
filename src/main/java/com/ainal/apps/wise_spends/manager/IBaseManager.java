package com.ainal.apps.wise_spends.manager;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface IBaseManager {
	void setBaseEntityAttributes(@NonNull BaseEntity base, @NonNull HttpServletRequest request);

	void setBaseEntityAttributes(@NonNull BaseEntity base, @NonNull String name);
}
