package com.ainal.apps.wise_spends.common.service.bgt;

import com.ainal.apps.wise_spends.common.domain.budget.BudgetItem;

public interface IBudgetItemService {

	BudgetItem findBudgetItemById(Long budgetItemId);

	BudgetItem saveBudgetItem(BudgetItem budgetItem);

	void deleteBudgetItemById(Long budgetItemId);

}
