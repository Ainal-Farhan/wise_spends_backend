package com.ainal.apps.wise_spends.view.object;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;

import org.apache.logging.log4j.util.Strings;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.reference.MoneyTransactionTypeEnum;

public class MoneyTransactionVO implements IVO {

	private static final long serialVersionUID = 8218555273763458793L;
	private Long id;
	private BigDecimal amount;
	private MoneyTransactionReferenceVO moneyTransactionReferenceVO;
	private MoneyTransactionTypeEnum type;
	private String transactionDate;
	private String fromString;

	public MoneyTransactionVO() {

	}

	public MoneyTransactionVO(MoneyTransaction moneyTransaction) {
		initVO(moneyTransaction);
	}

	public MoneyTransactionVO(MoneyTransaction moneyTransaction, String fromString) {
		this.fromString = fromString;
		initVO(moneyTransaction);
	}

	private void initVO(MoneyTransaction moneyTransaction) {
		if (moneyTransaction != null) {
			this.id = moneyTransaction.getId();
			this.amount = (moneyTransaction.getAmount() == null ? BigDecimal.valueOf(0) : moneyTransaction.getAmount())
					.setScale(2, RoundingMode.HALF_UP);
			this.transactionDate = Strings.EMPTY;
			if (moneyTransaction.getTransactionDate() != null) {
				this.transactionDate = moneyTransaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().toString();
			}
			this.type = moneyTransaction.getType();

			if (moneyTransaction.getMoneyTransactionReference() != null) {
				this.moneyTransactionReferenceVO = new MoneyTransactionReferenceVO(
						moneyTransaction.getMoneyTransactionReference());
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public MoneyTransactionReferenceVO getMoneyTransactionReferenceVO() {
		return moneyTransactionReferenceVO;
	}

	public void setMoneyTransactionReferenceVO(MoneyTransactionReferenceVO moneyTransactionReferenceVO) {
		this.moneyTransactionReferenceVO = moneyTransactionReferenceVO;
	}

	public MoneyTransactionTypeEnum getType() {
		return type;
	}

	public void setType(MoneyTransactionTypeEnum type) {
		this.type = type;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getFromString() {
		return fromString;
	}

	public void setFromString(String fromString) {
		this.fromString = fromString;
	}

}
