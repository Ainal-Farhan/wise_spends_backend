package com.ainal.apps.wise_spends.common.service.usr;

import com.ainal.apps.wise_spends.common.domain.usr.User;

import io.micrometer.common.lang.NonNull;

public interface IUserService {
	User findByUserId(Long userId);

	User findByUserByUsername(@NonNull String username);
}
