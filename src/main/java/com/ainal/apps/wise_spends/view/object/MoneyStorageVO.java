package com.ainal.apps.wise_spends.view.object;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;

public class MoneyStorageVO implements IVO {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private BigDecimal unallocatedAmount;
	private BigDecimal totalAmount;
	private String abbreviation;

	public MoneyStorageVO() {
	}

	public MoneyStorageVO(MoneyStorage moneyStorage) {
		if (moneyStorage != null) {
			this.id = moneyStorage.getId();
			this.name = moneyStorage.getFullName();
			this.totalAmount = (moneyStorage.getTotalAmount() == null ? BigDecimal.valueOf(0)
					: moneyStorage.getTotalAmount()).setScale(2, RoundingMode.HALF_UP);
			this.abbreviation = moneyStorage.getAbbreviation();

			BigDecimal unallocatedAmount = this.totalAmount;

			if (!CollectionUtils.isEmpty(moneyStorage.getCreditCardList())) {
				for (CreditCard cardCredit : moneyStorage.getCreditCardList()) {
					if (cardCredit.getCreditAmount() != null) {
						unallocatedAmount = unallocatedAmount.subtract(cardCredit.getCreditAmount());
					}
				}
			}

			if (!CollectionUtils.isEmpty(moneyStorage.getSavingList())) {
				for (Saving saving : moneyStorage.getSavingList()) {
      				BigDecimal currentAmount = saving.getCurrentAmount();
					if (currentAmount != null) {
						boolean isNegative = currentAmount.signum() == -1;
      					      	currentAmount = isNegative? currentAmount.negate() : currentAmount;
						unallocatedAmount = (unallocatedAmount.signum() != -1 || isNegative)?
							unallocatedAmount.subtract(currentAmount)
							: unallocatedAmount.add(currentAmount);
					}
				}
			}

			this.unallocatedAmount = unallocatedAmount.setScale(2, RoundingMode.HALF_UP);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MoneyStorageVO moneyStorageVO = (MoneyStorageVO) o;

		return new EqualsBuilder().append(name, moneyStorageVO.name)
				.append(unallocatedAmount, moneyStorageVO.unallocatedAmount)
				.append(totalAmount, moneyStorageVO.totalAmount).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(name).append(unallocatedAmount).append(totalAmount).toHashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnallocatedAmount() {
		return unallocatedAmount;
	}

	public void setUnallocatedAmount(BigDecimal unallocatedAmount) {
		this.unallocatedAmount = unallocatedAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

}
