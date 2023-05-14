package com.ainal.apps.wise_spends.common.service.ref.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionCategoryReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.ref.IMoneyTransactionCategoryReferenceRepository;
import com.ainal.apps.wise_spends.common.service.ref.IMoneyTransactionCategoryReferenceService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MoneyTransactionCategoryReferenceService implements IMoneyTransactionCategoryReferenceService {
	@Autowired
	IMoneyTransactionCategoryReferenceRepository categoryReferenceRepository;

	@Override
	public List<MoneyTransactionCategoryReference> findByUser(User user) {
		return categoryReferenceRepository.findByUser(user);
	}

	@Override
	public MoneyTransactionCategoryReference findById(Long id) {
		return categoryReferenceRepository.findById(id).orElse(null);
	}

	@Override
	public MoneyTransactionCategoryReference save(MoneyTransactionCategoryReference categoryReference) {
		return categoryReferenceRepository.save(categoryReference);
	}

	@Override
	public void deleteById(Long id) {
		categoryReferenceRepository.deleteById(id);
	}

}
