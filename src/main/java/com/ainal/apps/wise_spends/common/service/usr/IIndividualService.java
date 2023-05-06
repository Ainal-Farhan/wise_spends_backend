package com.ainal.apps.wise_spends.common.service.usr;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.usr.Individual;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IIndividualService {
	List<Individual> findIndividualByEmail(String email);

	Individual findIndividualByUser(User user);
}
