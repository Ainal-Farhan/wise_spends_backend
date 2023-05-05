package com.ainal.apps.wise_spends.security.config.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.usr.Credential;
import com.ainal.apps.wise_spends.common.domain.usr.Individual;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.manager.ICredentialManager;
import com.ainal.apps.wise_spends.manager.IUserManager;
import com.ainal.apps.wise_spends.security.config.service.IUserJwtViewObjectService;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

@Service
public class UserJwtViewObjectService implements IUserJwtViewObjectService {
	@Autowired
	IUserManager userManager;

	@Autowired
	ICredentialManager credentialManager;

	@Override
	public UserJwtViewObject loadUserJwtViewObjectByUsernameOrEmail(String usernameOrEmail) {
		List<Credential> credentialList = credentialManager.findCredentialByUsernameOrEmail(usernameOrEmail);
		UserJwtViewObject userJwtViewObject = null;

		if (!CollectionUtils.isEmpty(credentialList)) {
			Credential credential = credentialList.get(0);
			User user = credential.getUser();
			if (user != null) {
				Individual individual = user.getIndividual();
				userJwtViewObject = new UserJwtViewObject();

				userJwtViewObject.setUsernameOrEmail(credential.getUsername());
				userJwtViewObject.setPassword(credential.getEncryptedPassword());

				if (credential.getFlagUsernameIsEmail()) {
					userJwtViewObject.setUsernameOrEmail(individual.getEmail());
				}

				userJwtViewObject.setGrantedAuthorities(userManager.getUserAuthorities(user));

				userJwtViewObject.setName(individual.getNickName());
			}
		}

		return userJwtViewObject;
	}

}
