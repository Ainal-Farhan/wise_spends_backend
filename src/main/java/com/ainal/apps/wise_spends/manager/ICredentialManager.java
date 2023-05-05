package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.usr.Credential;

public interface ICredentialManager {
	List<Credential> findCredentialByUsernameOrEmail(String usernameOrEmail);
}
