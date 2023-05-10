package com.ainal.apps.wise_spends.view.object;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MoneyStorageVO implements IVO {
	private static final long serialVersionUID = 1L;

	private String name;
	private BigDecimal unallocatedAmount;
	private BigDecimal totalAmount;

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
		return new HashCodeBuilder().append(name).append(unallocatedAmount).append(totalAmount).toHashCode();
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

}
