package com.ainal.apps.wise_spends.view.object.form;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;

public class CreditCardFormVO implements IFormVO {

	private static final long serialVersionUID = -2022034932398918055L;

	private Long id;
	private Long moneyStorageId;
	private String shortName;
	private String fullName;
	private String abbreviation;
	private BigDecimal creditAmount;

	public CreditCardFormVO() {

	}

	public CreditCardFormVO(CreditCard creditCard) {
		if (creditCard != null) {
			this.id = creditCard.getId();
			if (creditCard.getMoneyStorage() != null) {
				this.moneyStorageId = creditCard.getMoneyStorage().getId();
			}
			this.shortName = creditCard.getShortName();
			this.fullName = creditCard.getFullName();
			this.abbreviation = creditCard.getAbbreviation();
			this.creditAmount = (creditCard.getCreditAmount() == null ? BigDecimal.valueOf(0)
					: creditCard.getCreditAmount()).setScale(2, RoundingMode.HALF_UP);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMoneyStorageId() {
		return moneyStorageId;
	}

	public void setMoneyStorageId(Long moneyStorageId) {
		this.moneyStorageId = moneyStorageId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

}
