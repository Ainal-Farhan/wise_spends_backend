package com.ainal.apps.wise_spends.common.service.bgt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.budget.BudgetItem;
import com.ainal.apps.wise_spends.common.repository.bgt.IBudgetItemRepository;
import com.ainal.apps.wise_spends.common.service.bgt.IBudgetItemService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BudgetItemService implements IBudgetItemService {
	@Autowired
	private IBudgetItemRepository budgetItemRepository;

	@Override
	public BudgetItem findBudgetItemById(Long budgetItemId) {
		return budgetItemRepository.findById(budgetItemId).orElse(null);
	}

	@Override
	public BudgetItem saveBudgetItem(BudgetItem budgetItem) {
		return budgetItemRepository.save(budgetItem);
	}

	@Override
	public void deleteBudgetItemById(Long budgetItemId) {
		budgetItemRepository.deleteById(budgetItemId);
	}

}
