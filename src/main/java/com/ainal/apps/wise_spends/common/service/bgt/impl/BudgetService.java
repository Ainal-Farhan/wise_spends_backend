package com.ainal.apps.wise_spends.common.service.bgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.budget.Budget;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.bgt.IBudgetRepository;
import com.ainal.apps.wise_spends.common.service.bgt.IBudgetService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BudgetService implements IBudgetService {
	@Autowired
	private IBudgetRepository budgetRepository;

	@Override
	public List<Budget> findBudgetListByUser(User user) {
		return budgetRepository.findByUser(user);
	}

	@Override
	public Budget findBudgetById(Long budgetId) {
		return budgetRepository.findById(budgetId).orElse(null);
	}

	@Override
	public Budget saveBudget(Budget budget) {
		return budgetRepository.save(budget);
	}

	@Override
	public void deleteBudgetById(Long budgetId) {
		budgetRepository.deleteById(budgetId);
	}

}
