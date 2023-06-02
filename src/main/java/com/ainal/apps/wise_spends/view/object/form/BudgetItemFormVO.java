package com.ainal.apps.wise_spends.view.object.form;

import java.math.BigDecimal;

import com.ainal.apps.wise_spends.common.domain.budget.BudgetItem;

public class BudgetItemFormVO implements IFormVO {

	private static final long serialVersionUID = -1595387694626796033L;

	private Long budgetId;
	private Long savingId;
	private Long id;
	private String title;
	private BigDecimal amount;

	public BudgetItemFormVO() {

	}

	public BudgetItemFormVO(BudgetItem budgetItem) {
		if (budgetItem != null) {
			this.id = budgetItem.getId();
			this.budgetId = budgetItem.getBudget() == null ? null : budgetItem.getBudget().getId();
			this.savingId = budgetItem.getSaving() == null ? null : budgetItem.getSaving().getId();
			this.title = budgetItem.getTitle();
			this.amount = budgetItem.getAmount();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

	public Long getSavingId() {
		return savingId;
	}

	public void setSavingId(Long savingId) {
		this.savingId = savingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
