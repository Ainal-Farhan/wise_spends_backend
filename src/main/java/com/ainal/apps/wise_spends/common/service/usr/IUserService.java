package com.ainal.apps.wise_spends.common.service.usr;

import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IUserService {
	User findByUserId(Long userId);
}
