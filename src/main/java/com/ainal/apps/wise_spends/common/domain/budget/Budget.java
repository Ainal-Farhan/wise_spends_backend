package com.ainal.apps.wise_spends.common.domain.budget;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.usr.User;

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
@Table(name = TablePrefixConstant.BGT_TABLE_PREFIX + "BUDGET")
public class Budget extends BaseEntity {

	private static final long serialVersionUID = -5758620863468881082L;
	private static final String FK_PREFIX = "FK_BB_" + TablePrefixConstant.BGT_TABLE_PREFIX;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetSeq")
	@SequenceGenerator(name = "budgetSeq", sequenceName = "SEQ_BUDGET", allocationSize = 1)
	@Column(name = "BUDGET_ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = FK_PREFIX + "USER_ID"))
	private User user;

	@OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BudgetItem> budgetItemList = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public User fetchUser() {
		Hibernate.initialize(user);
		return user;
	}

	public List<BudgetItem> fetchBudgetItemList() {
		Hibernate.initialize(budgetItemList);
		return budgetItemList;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BudgetItem> getBudgetItemList() {
		return budgetItemList;
	}

	public void setBudgetItemList(List<BudgetItem> budgetItemList) {
		this.budgetItemList = budgetItemList;
	}

}
