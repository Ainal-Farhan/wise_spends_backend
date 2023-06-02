package com.ainal.apps.wise_spends.manager.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ainal.apps.wise_spends.common.domain.budget.Budget;
import com.ainal.apps.wise_spends.common.domain.budget.BudgetItem;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.bgt.IBudgetItemService;
import com.ainal.apps.wise_spends.common.service.bgt.IBudgetService;
import com.ainal.apps.wise_spends.common.service.mny.ISavingService;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.IBudgetManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.view.object.BudgetItemVO;
import com.ainal.apps.wise_spends.view.object.BudgetVO;
import com.ainal.apps.wise_spends.view.object.form.BudgetFormVO;
import com.ainal.apps.wise_spends.view.object.form.BudgetItemFormVO;

import jakarta.servlet.http.HttpServletRequest;

public class BudgetManager implements IBudgetManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	private IBaseManager baseManager;

	@Autowired
	private IBudgetService budgetService;

	@Autowired
	private IBudgetItemService budgetItemService;

	@Autowired
	private ISavingService savingService;

	@Override
	public List<BudgetVO> populateBudgetVOList(HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		return budgetService.findBudgetListByUser(currentUser).stream().map(budget -> new BudgetVO(budget))
				.collect(Collectors.toList());
	}

	@Override
	public List<BudgetItemVO> populateBudgetItemVOList(HttpServletRequest request, Long budgetId) {
		return getBudgetById(budgetId).fetchBudgetItemList().stream().map(budgetItem -> new BudgetItemVO(budgetItem))
				.collect(Collectors.toList());
	}

	@Override
	public Budget getBudgetById(Long budgetId) {
		return budgetService.findBudgetById(budgetId);
	}

	@Override
	public BudgetItem getBudgetItemById(Long budgetItemId) {
		return budgetItemService.findBudgetItemById(budgetItemId);
	}

	@Override
	public Budget addNewBudgetForCurrentUser(HttpServletRequest request, BudgetFormVO budgetFormVO) {
		if (budgetFormVO == null) {
			return null;
		}

		Budget budget = new Budget();
		setBudgetData(budget, budgetFormVO, request);

		return budgetService.saveBudget(budget);
	}

	@Override
	public BudgetItem addNewBudgetItemForCurrentUser(HttpServletRequest request, BudgetItemFormVO budgetItemFormVO) {
		if (budgetItemFormVO == null) {
			return null;
		}

		BudgetItem budgetItem = new BudgetItem();
		setBudgeItemData(budgetItem, budgetItemFormVO, request);

		return budgetItemService.saveBudgetItem(budgetItem);
	}

	@Override
	public Budget updateBudgetForCurrentUser(HttpServletRequest request, BudgetFormVO budgetFormVO) {
		if (budgetFormVO == null || budgetFormVO.getId() == null) {
			return null;
		}

		Budget budget = budgetService.findBudgetById(budgetFormVO.getId());
		setBudgetData(budget, budgetFormVO, request);

		return budgetService.saveBudget(budget);
	}

	@Override
	public BudgetItem updateBudgetItemForCurrentUser(HttpServletRequest request, BudgetItemFormVO budgetItemFormVO) {
		if (budgetItemFormVO == null || budgetItemFormVO.getId() == null) {
			return null;
		}

		BudgetItem budgetItem = budgetItemService.findBudgetItemById(budgetItemFormVO.getId());
		setBudgeItemData(budgetItem, budgetItemFormVO, request);

		return budgetItemService.saveBudgetItem(budgetItem);
	}

	@Override
	public boolean deleteBudgetById(Long budgetId) {
		budgetService.deleteBudgetById(budgetId);
		return true;
	}

	@Override
	public boolean deleteBudgetItemById(Long budgetItemId) {
		budgetItemService.deleteBudgetItemById(budgetItemId);
		return true;
	}

	private void setBudgetData(Budget budget, BudgetFormVO budgetFormVO, HttpServletRequest request) {
		if (budget == null || budgetFormVO == null) {
			return;
		}

		baseManager.setBaseEntityAttributes(budget, request);

		User currentUser = currentUserManager.getCurrentUser(request);
		budget.setUser(currentUser);
		budget.setTitle(budgetFormVO.getTitle());
	}

	private void setBudgeItemData(BudgetItem budgetItem, BudgetItemFormVO budgetItemFormVO,
			HttpServletRequest request) {
		if (budgetItem == null || budgetItemFormVO == null) {
			return;
		}

		baseManager.setBaseEntityAttributes(budgetItem, request);

		if (budgetItemFormVO.getBudgetId() != null) {
			budgetItem.setBudget(budgetService.findBudgetById(budgetItemFormVO.getBudgetId()));
		}

		if (budgetItemFormVO.getSavingId() != null) {
			budgetItem.setSaving(savingService.findSavingById(budgetItemFormVO.getSavingId()));
		}

		budgetItem.setTitle(budgetItemFormVO.getTitle());
		budgetItem.setAmount(budgetItemFormVO.getAmount());
	}

}
