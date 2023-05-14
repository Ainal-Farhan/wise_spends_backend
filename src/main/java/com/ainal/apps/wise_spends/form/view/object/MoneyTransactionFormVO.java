package com.ainal.apps.wise_spends.form.view.object;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;

public class MoneyTransactionFormVO implements IFormVO {

	private static final long serialVersionUID = -315429285648869392L;

	private Long id;

	public MoneyTransactionFormVO() {

	}

	public MoneyTransactionFormVO(MoneyTransaction moneyTransaction) {
		if (moneyTransaction != null) {
			this.id = moneyTransaction.getId();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
