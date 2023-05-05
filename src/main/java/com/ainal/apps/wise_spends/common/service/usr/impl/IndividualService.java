package com.ainal.apps.wise_spends.common.service.usr.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.usr.Individual;
import com.ainal.apps.wise_spends.common.repository.usr.IIndividualRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.usr.IIndividualService;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class IndividualService extends BaseService implements IIndividualService {
	@Autowired
	IIndividualRepository individualRepository;	
	
	@Override
	public List<Individual> findIndividualByEmail(String email) {
		if(StringUtils.isBlank(email)) {
			return List.of();
		}
		return individualRepository.findByEmail(email);
	}
	
}
