package com.ainal.apps.wise_spends.view.object.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.reference.MoneyTransactionTypeEnum;
import com.ainal.apps.wise_spends.view.object.SelectItemVO;

public class MoneyTransactionFormVO implements IFormVO {

	private static final long serialVersionUID = -315429285648869392L;

	private Long id;
	private MoneyTransactionTypeEnum type;
	private SelectItemVO from;
	private BigDecimal amount;
	private LocalDate transactionDate;
	private Long moneyTransactionReferenceId;

	public MoneyTransactionFormVO() {

	}

	public MoneyTransactionFormVO(MoneyTransaction moneyTransaction, SelectItemVO from) {
		if (moneyTransaction != null) {
			this.id = moneyTransaction.getId();
			this.type = moneyTransaction.getType();
			this.transactionDate = moneyTransaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			this.amount = (moneyTransaction.getAmount() == null ? BigDecimal.valueOf(0) : moneyTransaction.getAmount())
					.setScale(2, RoundingMode.HALF_UP);
			this.from = from;

			if (moneyTransaction.getMoneyTransactionReference() != null) {
				this.moneyTransactionReferenceId = moneyTransaction.getMoneyTransactionReference().getId();
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MoneyTransactionTypeEnum getType() {
		return type;
	}

	public void setType(MoneyTransactionTypeEnum type) {
		this.type = type;
	}

	public SelectItemVO getFrom() {
		return from;
	}

	public void setFrom(SelectItemVO from) {
		this.from = from;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getMoneyTransactionReferenceId() {
		return moneyTransactionReferenceId;
	}

	public void setMoneyTransactionReferenceId(Long moneyTransactionReferenceId) {
		this.moneyTransactionReferenceId = moneyTransactionReferenceId;
	}

}
