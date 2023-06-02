package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.budget.Budget;
import com.ainal.apps.wise_spends.common.domain.budget.BudgetItem;
import com.ainal.apps.wise_spends.view.object.BudgetItemVO;
import com.ainal.apps.wise_spends.view.object.BudgetVO;
import com.ainal.apps.wise_spends.view.object.form.BudgetFormVO;
import com.ainal.apps.wise_spends.view.object.form.BudgetItemFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface IBudgetManager {
	List<BudgetVO> populateBudgetVOList(@NonNull HttpServletRequest request);

	List<BudgetItemVO> populateBudgetItemVOList(@NonNull HttpServletRequest request, Long budgetId);

	Budget getBudgetById(@NonNull Long budgetId);

	BudgetItem getBudgetItemById(@NonNull Long budgetItemId);

	Budget addNewBudgetForCurrentUser(@NonNull HttpServletRequest request, BudgetFormVO budgetFormVO);

	BudgetItem addNewBudgetItemForCurrentUser(@NonNull HttpServletRequest request, BudgetItemFormVO budgetItemFormVO);

	Budget updateBudgetForCurrentUser(@NonNull HttpServletRequest request, BudgetFormVO budgetFormVO);

	BudgetItem updateBudgetItemForCurrentUser(@NonNull HttpServletRequest request, BudgetItemFormVO budgetItemFormVO);

	boolean deleteBudgetById(@NonNull Long budgetId);

	boolean deleteBudgetItemById(@NonNull Long budgetItemId);

}
