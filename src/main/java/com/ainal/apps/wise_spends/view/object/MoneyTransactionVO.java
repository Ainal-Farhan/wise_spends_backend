package com.ainal.apps.wise_spends.view.object;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;

public class MoneyTransactionVO implements IVO {

	private static final long serialVersionUID = 8218555273763458793L;
	private Long id;

	public MoneyTransactionVO() {

	}

	public MoneyTransactionVO(MoneyTransaction moneyTransaction) {
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
