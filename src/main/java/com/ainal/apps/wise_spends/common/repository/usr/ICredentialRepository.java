package com.ainal.apps.wise_spends.common.repository.usr;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ainal.apps.wise_spends.common.domain.usr.Credential;

public interface ICredentialRepository extends JpaRepository<Credential, Long> {
	@Query("SELECT c FROM Credential c where c.username = ?1 and c.flagUsernameIsEmail = false and c.flagActive = true")
	Optional<Credential> findByUsernameAndUsernameIsNotEmail(String username);
}
