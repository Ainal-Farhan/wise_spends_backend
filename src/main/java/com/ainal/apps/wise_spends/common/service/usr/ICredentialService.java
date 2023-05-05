package com.ainal.apps.wise_spends.common.service.usr;

import com.ainal.apps.wise_spends.common.domain.usr.Credential;

public interface ICredentialService {
	Credential findCredentialByUsername(String username);
}
