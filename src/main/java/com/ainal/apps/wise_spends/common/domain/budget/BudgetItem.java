package com.ainal.apps.wise_spends.common.domain.budget;

import java.math.BigDecimal;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = TablePrefixConstant.BGT_TABLE_PREFIX + "BUDGET_ITEM")
public class BudgetItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private static final String FK_PREFIX = "FK_BBI_" + TablePrefixConstant.BGT_TABLE_PREFIX;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetItemSeq")
	@SequenceGenerator(name = "budgetItemSeq", sequenceName = "SEQ_BUDGET_ITEM", allocationSize = 1)
	@Column(name = "BUDGET_ITEM_ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "BUDGET_ID", foreignKey = @ForeignKey(name = FK_PREFIX + "BUDGET_ID"))
	private Budget budget;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SAVING_ID", foreignKey = @ForeignKey(name = FK_PREFIX + "SAVING_ID"))
	private Saving saving;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Saving getSaving() {
		return saving;
	}

	public void setSaving(Saving saving) {
		this.saving = saving;
	}

}
