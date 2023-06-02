package com.ainal.apps.wise_spends.view.object.form;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ainal.apps.wise_spends.common.domain.mny.Saving;

public class SavingFormVO implements IFormVO {

	private static final long serialVersionUID = 479490612057934748L;
	private Long id;
	private Long moneyStorageId;
	private String shortName;
	private String fullName;
	private String abbreviation;
	private BigDecimal currentAmount;

	public SavingFormVO() {

	}

	public SavingFormVO(Saving saving) {
		if (saving != null) {
			this.id = saving.getId();
			if (saving.getMoneyStorage() != null) {
				this.moneyStorageId = saving.getMoneyStorage().getId();
			}
			this.shortName = saving.getShortName();
			this.fullName = saving.getFullName();
			this.abbreviation = saving.getAbbreviation();
			this.currentAmount = (saving.getCurrentAmount() == null ? BigDecimal.valueOf(0) : saving.getCurrentAmount())
					.setScale(2, RoundingMode.HALF_UP);
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

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

}
