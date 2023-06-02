package com.ainal.apps.wise_spends.view.object.form;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;

public class MoneyTransactionReferenceFormVO implements IFormVO {

	private static final long serialVersionUID = -4846929298692725468L;

	private Long id;
	private String title;
	private String description;
	private String code;
	private Long categoryReferenceId;

	public MoneyTransactionReferenceFormVO() {

	}

	public MoneyTransactionReferenceFormVO(MoneyTransactionReference moneyTransactionReference) {
		if (moneyTransactionReference != null) {
			this.id = moneyTransactionReference.getId();
			this.title = moneyTransactionReference.getTitle();
			this.description = moneyTransactionReference.getDescription();
			this.code = moneyTransactionReference.getCode();

			if (moneyTransactionReference.getMoneyTransactionCategoryReference() != null) {
				categoryReferenceId = moneyTransactionReference.getMoneyTransactionCategoryReference().getId();
			}
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

	public Long getCategoryReferenceId() {
		return categoryReferenceId;
	}

	public void setCategoryReferenceId(Long categoryReferenceId) {
		this.categoryReferenceId = categoryReferenceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
