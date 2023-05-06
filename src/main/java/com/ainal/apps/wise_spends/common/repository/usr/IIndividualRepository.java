package com.ainal.apps.wise_spends.common.repository.usr;

import java.util.List;
import java.util.Optional;

import com.ainal.apps.wise_spends.common.domain.usr.Individual;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.BaseEntityRepository;

public interface IIndividualRepository extends BaseEntityRepository<Individual> {
	List<Individual> findByEmail(String email);
	Optional<Individual> findByUser(User user);
}
