package com.ainal.apps.wise_spends.common.service.ref.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.ref.IMoneyTransactionReferenceRepository;
import com.ainal.apps.wise_spends.common.service.ref.IMoneyTransactionReferenceService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MoneyTransactionReferenceService implements IMoneyTransactionReferenceService {
	@Autowired
	IMoneyTransactionReferenceRepository moneyTransactionReferenceRepository;

	@Override
	public List<MoneyTransactionReference> findMoneyTransactionReferenceListByUser(User user) {
		return moneyTransactionReferenceRepository.findByUser(user);
	}

	@Override
	public MoneyTransactionReference findMoneyTransactionReferenceById(Long moneyTransactionReferenceId) {
		return moneyTransactionReferenceRepository.findById(moneyTransactionReferenceId).orElse(null);
	}

	@Override
	public MoneyTransactionReference saveMoneyTransactionReference(
			MoneyTransactionReference moneyTransactionReference) {
		return moneyTransactionReferenceRepository.save(moneyTransactionReference);
	}

	@Override
	public void deleteMoneyTransactionReferenceById(Long moneyTransactionReferenceId) {
		moneyTransactionReferenceRepository.deleteById(moneyTransactionReferenceId);
	}

}
