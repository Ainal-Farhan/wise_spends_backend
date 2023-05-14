package com.ainal.apps.wise_spends.view.object;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionCategoryReference;

public class MoneyTransactionCategoryReferenceVO implements IVO {

	private static final long serialVersionUID = 7772518363752183345L;

	private Long id;

	private String title;
	private String description;

	public MoneyTransactionCategoryReferenceVO() {

	}

	public MoneyTransactionCategoryReferenceVO(MoneyTransactionCategoryReference categoryReference) {
		if (categoryReference != null) {
			this.id = categoryReference.getId();
			this.title = categoryReference.getTitle();
			this.description = categoryReference.getDescription();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
