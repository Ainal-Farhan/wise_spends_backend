package com.ainal.apps.wise_spends.view.object.form;

import com.ainal.apps.wise_spends.common.domain.budget.Budget;

public class BudgetFormVO implements IFormVO {

	private static final long serialVersionUID = 5469006652661846120L;

	private Long id;
	private String title;

	public BudgetFormVO() {

	}

	public BudgetFormVO(Budget budget) {
		if (budget != null) {
			this.id = budget.getId();
			this.title = budget.getTitle();
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

}
