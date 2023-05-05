package com.ainal.apps.wise_spends.common.service.usr.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.usr.Credential;
import com.ainal.apps.wise_spends.common.repository.usr.ICredentialRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.usr.ICredentialService;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CredentialService extends BaseService implements ICredentialService {
	@Autowired
	ICredentialRepository credentialRepository;

	@Override
	public Credential findCredentialByUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		return credentialRepository.findByUsernameAndUsernameIsNotEmail(username).orElse(null);
	}

}
