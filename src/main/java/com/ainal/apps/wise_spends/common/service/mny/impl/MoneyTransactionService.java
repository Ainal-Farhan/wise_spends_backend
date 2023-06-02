package com.ainal.apps.wise_spends.common.service.mny.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.mny.IMoneyTransactionRepository;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyTransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MoneyTransactionService implements IMoneyTransactionService {
	@Autowired
	IMoneyTransactionRepository moneyTransactionRepository;

	@Override
	public List<MoneyTransaction> findByUser(User user) {
		return moneyTransactionRepository.findByUser(user);
	}

	@Override
	public MoneyTransaction findById(Long id) {
		return moneyTransactionRepository.findById(id).orElse(null);
	}

	@Override
	public MoneyTransaction save(MoneyTransaction categoryReference) {
		return moneyTransactionRepository.save(categoryReference);
	}

	@Override
	public void deleteById(Long id) {
		moneyTransactionRepository.deleteById(id);
	}

	@Override
	public List<MoneyTransaction> findByUserAndAtleastFromNumDays(User user, int days) {
		LocalDate startDate = LocalDate.now().minusDays(days);
		Date startDateAsDate = java.sql.Date.valueOf(startDate);
		return moneyTransactionRepository.findByUserAndStartDate(user, startDateAsDate);
	}

}
