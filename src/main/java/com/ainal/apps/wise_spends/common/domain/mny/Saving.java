package com.ainal.apps.wise_spends.common.domain.mny;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.budget.BudgetItem;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.userType.YesNoUserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = TablePrefixConstant.MNY_TABLE_PREFIX + "SAVING")
public class Saving extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "savingSeq")
	@SequenceGenerator(name = "savingSeq", sequenceName = "SEQ_SAVING", allocationSize = 1)
	@Column(name = "SAVING_ID")
	private Long id;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "ABBREVIATION")
	private String abbreviation;

	@Column(name = "SHORT_NAME")
	private String shortName;

	@Column(name = "FLAG_GOAL", columnDefinition = "CHAR(1) DEFAULT 'N'")
	@Type(value = YesNoUserType.class)
	private Boolean flagGoal = Boolean.FALSE;

	@Column(name = "CURRENT_AMOUNT")
	private BigDecimal currentAmount;

	@Column(name = "GOAL_AMOUNT")
	private BigDecimal goalAmount;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "MONEY_STORAGE_ID", foreignKey = @ForeignKey(name = "FK_MS_MNY_SAVING_ID"))
	private MoneyStorage moneyStorage;

	@OneToMany(mappedBy = "saving", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BudgetItem> budgetItemList = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Boolean getFlagGoal() {
		return flagGoal;
	}

	public void setFlagGoal(Boolean flagGoal) {
		this.flagGoal = flagGoal;
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public MoneyStorage getMoneyStorage() {
		return moneyStorage;
	}

	public void setMoneyStorage(MoneyStorage moneyStorage) {
		this.moneyStorage = moneyStorage;
	}

	public BigDecimal getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(BigDecimal goalAmount) {
		this.goalAmount = goalAmount;
	}

	public List<BudgetItem> getBudgetItemList() {
		return budgetItemList;
	}

	public void setBudgetItemList(List<BudgetItem> budgetItemList) {
		this.budgetItemList = budgetItemList;
	}

}
