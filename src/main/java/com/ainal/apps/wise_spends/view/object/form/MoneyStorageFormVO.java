package com.ainal.apps.wise_spends.view.object.form;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.reference.MoneyStorageTypeEnum;

import jakarta.annotation.Nullable;

public class MoneyStorageFormVO implements IFormVO {

	private static final long serialVersionUID = -9061307223627599359L;

	@Nullable
	private Long id;

	@Nullable
	private String fullName;

	@Nullable
	private String abbreviation;

	@Nullable
	private MoneyStorageTypeEnum type;

	@Nullable
	private BigDecimal totalAmount;

	public MoneyStorageFormVO() {
	}

	public MoneyStorageFormVO(MoneyStorage moneyStorage) {
		if (moneyStorage != null) {
			this.id = moneyStorage.getId();
			this.fullName = moneyStorage.getFullName();
			this.abbreviation = moneyStorage.getAbbreviation();
			this.type = moneyStorage.getType();
			this.totalAmount = (moneyStorage.getTotalAmount() == null ? BigDecimal.valueOf(0)
					: moneyStorage.getTotalAmount()).setScale(2, RoundingMode.HALF_UP);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public MoneyStorageTypeEnum getType() {
		return type;
	}

	public void setType(MoneyStorageTypeEnum type) {
		this.type = type;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
