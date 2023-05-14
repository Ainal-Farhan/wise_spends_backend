package com.ainal.apps.wise_spends.view.object;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;

public class CreditCardVO implements IVO {

	private static final long serialVersionUID = -7476974498473961490L;
	private Long id;
	private String shortName;
	private String abbreviation;
	private String moneyStorageName;
	private BigDecimal creditAmount;

	public CreditCardVO() {

	}

	public CreditCardVO(CreditCard creditCard) {
		if (creditCard != null) {
			this.id = creditCard.getId();
			this.shortName = creditCard.getShortName();
			this.abbreviation = creditCard.getAbbreviation();
			this.creditAmount = (creditCard.getCreditAmount() == null ? BigDecimal.valueOf(0)
					: creditCard.getCreditAmount()).setScale(2, RoundingMode.HALF_UP);
			this.moneyStorageName = "-";
			if (creditCard.getMoneyStorage() != null) {
				this.moneyStorageName = String.format("%s (%s)", creditCard.getMoneyStorage().getFullName(),
						creditCard.getMoneyStorage().getAbbreviation());
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getMoneyStorageName() {
		return moneyStorageName;
	}

	public void setMoneyStorageName(String moneyStorageName) {
		this.moneyStorageName = moneyStorageName;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

}
