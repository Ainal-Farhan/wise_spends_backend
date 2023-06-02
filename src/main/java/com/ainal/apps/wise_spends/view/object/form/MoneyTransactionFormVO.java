package com.ainal.apps.wise_spends.view.object.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.reference.MoneyTransactionTypeEnum;
import com.ainal.apps.wise_spends.common.reference.YesNoEnum;
import com.ainal.apps.wise_spends.view.object.SelectItemVO;

public class MoneyTransactionFormVO implements IFormVO {

	private static final long serialVersionUID = -315429285648869392L;

	private Long id;
	private MoneyTransactionTypeEnum type;
	private SelectItemVO source;
	private BigDecimal amount;
	private LocalDate transactionDate;
	private Long moneyTransactionReferenceId;
	private YesNoEnum isWithinSystem;
	private SelectItemVO target;
	private MoneyTransactionTypeEnum typeTarget;

	public MoneyTransactionFormVO() {
		this.isWithinSystem = YesNoEnum.NO;
	}

	public MoneyTransactionFormVO(MoneyTransaction moneyTransaction, SelectItemVO from) {
		if (moneyTransaction != null) {
			this.id = moneyTransaction.getId();
			this.type = moneyTransaction.getType();
			this.transactionDate = moneyTransaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			this.amount = (moneyTransaction.getAmount() == null ? BigDecimal.valueOf(0) : moneyTransaction.getAmount())
					.setScale(2, RoundingMode.HALF_UP);
			this.source = from;
			this.target = null;
			this.isWithinSystem = YesNoEnum.NO;

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

	public SelectItemVO getSource() {
		return source;
	}

	public void setSource(SelectItemVO source) {
		this.source = source;
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

	public YesNoEnum getIsWithinSystem() {
		return isWithinSystem;
	}

	public void setIsWithinSystem(YesNoEnum isWithinSystem) {
		this.isWithinSystem = isWithinSystem;
	}

	public SelectItemVO getTarget() {
		return target;
	}

	public void setTarget(SelectItemVO target) {
		this.target = target;
	}

	public MoneyTransactionTypeEnum getTypeTarget() {
		return typeTarget;
	}

	public void setTypeTarget(MoneyTransactionTypeEnum typeTarget) {
		this.typeTarget = typeTarget;
	}

}
