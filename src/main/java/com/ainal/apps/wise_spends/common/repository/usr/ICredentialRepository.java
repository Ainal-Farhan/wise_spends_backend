package com.ainal.apps.wise_spends.common.repository.usr;

import java.util.Optional;

import com.ainal.apps.wise_spends.common.domain.usr.Credential;
import com.ainal.apps.wise_spends.common.repository.IBaseEntityRepository;

public interface ICredentialRepository extends IBaseEntityRepository<Credential> {
	Optional<Credential> findByUsername(String username);
}
