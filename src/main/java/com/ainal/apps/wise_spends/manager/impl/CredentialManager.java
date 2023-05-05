package com.ainal.apps.wise_spends.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.usr.Credential;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.usr.ICredentialService;
import com.ainal.apps.wise_spends.manager.ICredentialManager;
import com.ainal.apps.wise_spends.manager.IUserManager;
import com.ainal.apps.wise_spends.util.validator.EmailValidator;

import io.micrometer.common.util.StringUtils;

@Component
public class CredentialManager implements ICredentialManager {
	@Autowired
	ICredentialService credentialService;

	@Autowired
	IUserManager userManager;

	@Override
	public List<Credential> findCredentialByUsernameOrEmail(String usernameOrEmail) {
		if (StringUtils.isBlank(usernameOrEmail)) {
			return null;
		}

		List<Credential> credentialList = new ArrayList<>();

		if (EmailValidator.isValidEmail(usernameOrEmail)) {
			List<User> userList = userManager.findUserListByEmail(usernameOrEmail);
			if (!CollectionUtils.isEmpty(userList)) {
				for(User user : userList) {
					if (user.getCredential() != null && user.getCredential().getFlagActive()) {
						credentialList.add(user.getCredential());
					}
				}
			}
		} else {
			Credential credential = credentialService.findCredentialByUsername(usernameOrEmail);
			if(credential != null) {
				credentialList.add(credential);
			}
		}

		return credentialList;
	}
}
