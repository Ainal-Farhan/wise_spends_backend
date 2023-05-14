package com.ainal.apps.wise_spends.view.object;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ainal.apps.wise_spends.common.domain.mny.Saving;

public class SavingVO implements IVO {

	private static final long serialVersionUID = -2868080960330156556L;
	private Long id;
	private String shortName;
	private String abbreviation;
	private String moneyStorageName;
	private BigDecimal currentAmount;

	public SavingVO() {

	}

	public SavingVO(Saving saving) {
		if (saving != null) {
			this.id = saving.getId();
			this.shortName = saving.getShortName();
			this.abbreviation = saving.getAbbreviation();
			this.currentAmount = (saving.getCurrentAmount() == null ? BigDecimal.valueOf(0) : saving.getCurrentAmount())
					.setScale(2, RoundingMode.HALF_UP);
			this.moneyStorageName = "-";
			if (saving.getMoneyStorage() != null) {
				this.moneyStorageName = String.format("%s (%s)", saving.getMoneyStorage().getFullName(),
						saving.getMoneyStorage().getAbbreviation());
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

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

}
