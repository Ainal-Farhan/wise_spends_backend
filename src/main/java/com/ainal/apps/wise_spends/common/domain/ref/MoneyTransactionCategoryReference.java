package com.ainal.apps.wise_spends.common.domain.ref;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = TablePrefixConstant.REF_TABLE_PREFIX + "MONEY_TRANSACTION_CATEGORY_REFERENCE")
public class MoneyTransactionCategoryReference extends BaseEntity {
	private static final long serialVersionUID = 8545024613827193474L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moneyTransactionCategoryReferenceSeq")
	@SequenceGenerator(name = "moneyTransactionCategoryReferenceSeq", sequenceName = "SEQ_MONEY_TRANSACTION_CATEGORY_REFERENCE", allocationSize = 1)
	@Column(name = "MONEY_TRANSACTION_CATEGORY_REFERENCE_ID")
	private Long id;

	@Column(name = "TITLE", nullable = false, unique = true)
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "moneyTransactionCategoryReference", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MoneyTransactionReference> moneyTransactionReferenceList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_MMTCR_MNY_USER_ID"))
	private User user;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
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

	public List<MoneyTransactionReference> getMoneyTransactionReferenceList() {
		return moneyTransactionReferenceList;
	}

	public void setMoneyTransactionReferenceList(List<MoneyTransactionReference> moneyTransactionReferenceList) {
		this.moneyTransactionReferenceList = moneyTransactionReferenceList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
