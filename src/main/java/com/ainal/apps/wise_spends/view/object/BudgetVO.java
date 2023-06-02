package com.ainal.apps.wise_spends.view.object;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ainal.apps.wise_spends.common.domain.budget.Budget;

public class BudgetVO implements IVO {

	private static final long serialVersionUID = 4874572403318039441L;

	private Long id;
	private String title;
	private BigDecimal total;

	public BudgetVO() {

	}

	public BudgetVO(Budget budget) {
		if (budget != null) {
			this.id = budget.getId();
			this.title = budget.getTitle();
			this.total = budget.fetchBudgetItemList().stream()
					.map(bi -> bi.getAmount() == null ? BigDecimal.ZERO : bi.getAmount())
					.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
