package com.ainal.apps.wise_spends.common.repository.usr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ainal.apps.wise_spends.common.domain.usr.Individual;

public interface IIndividualRepository extends JpaRepository<Individual, Long> {
	List<Individual> findByEmail(String email);
}
