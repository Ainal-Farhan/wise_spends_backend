package com.ainal.apps.wise_spends.view.object;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ainal.apps.wise_spends.common.domain.budget.BudgetItem;

public class BudgetItemVO implements IVO {

	private static final long serialVersionUID = 5280384496704719043L;

	private String title;
	private BigDecimal amount;
	private Long id;
	private Long budgetId;

	public BudgetItemVO() {

	}

	public BudgetItemVO(BudgetItem budgetItem) {
		if (budgetItem != null) {
			this.title = budgetItem.getTitle();
			this.amount = (budgetItem.getAmount() != null ? budgetItem.getAmount() : BigDecimal.ZERO).setScale(2,
					RoundingMode.HALF_UP);
			this.id = budgetItem.getId();
			this.budgetId = budgetItem != null ? budgetItem.getId() : null;
		}
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

}
