package com.ainal.apps.wise_spends.common.service.bgt;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.budget.Budget;
import com.ainal.apps.wise_spends.common.domain.usr.User;

import io.micrometer.common.lang.NonNull;

public interface IBudgetService {
	List<Budget> findBudgetListByUser(@NonNull User user);

	Budget findBudgetById(Long budgetId);

	Budget saveBudget(Budget budget);

	void deleteBudgetById(Long budgetId);

}
