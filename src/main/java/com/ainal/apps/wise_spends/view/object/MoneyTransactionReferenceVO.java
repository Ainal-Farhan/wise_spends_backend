package com.ainal.apps.wise_spends.view.object;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;

public class MoneyTransactionReferenceVO implements IVO {

	private static final long serialVersionUID = -2004994209392363381L;

	private Long id;
	private MoneyTransactionCategoryReferenceVO categoryVO;
	private String title;
	private String description;

	public MoneyTransactionReferenceVO() {

	}

	public MoneyTransactionReferenceVO(MoneyTransactionReference moneyTransactionReference) {
		if (moneyTransactionReference != null) {
			this.id = moneyTransactionReference.getId();
			this.title = moneyTransactionReference.getTitle();
			this.description = moneyTransactionReference.getDescription();

			if (moneyTransactionReference.getMoneyTransactionCategoryReference() != null) {
				this.categoryVO = new MoneyTransactionCategoryReferenceVO(
						moneyTransactionReference.getMoneyTransactionCategoryReference());
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MoneyTransactionCategoryReferenceVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(MoneyTransactionCategoryReferenceVO categoryVO) {
		this.categoryVO = categoryVO;
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
