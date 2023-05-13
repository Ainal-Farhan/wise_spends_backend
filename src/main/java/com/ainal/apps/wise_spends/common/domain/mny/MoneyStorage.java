package com.ainal.apps.wise_spends.common.domain.mny;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.reference.MoneyStorageTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = TablePrefixConstant.MNY_TABLE_PREFIX + "MONEY_STORAGE")
@NamedEntityGraph(name = "MoneyStorage.detail", attributeNodes = { @NamedAttributeNode("savingList"),
		@NamedAttributeNode("creditCardList"), })
public class MoneyStorage extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moneyStorageSeq")
	@SequenceGenerator(name = "moneyStorageSeq", sequenceName = "SEQ_MONEY_STORAGE", allocationSize = 1)
	@Column(name = "MONEY_STORAGE_ID")
	private Long id;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "ABBREVIATION")
	private String abbreviation;

	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private MoneyStorageTypeEnum type;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_MMS_MNY_USER_ID"))
	private User user;

	@OneToMany(mappedBy = "moneyStorage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Saving> savingList = new ArrayList<>();

	@OneToMany(mappedBy = "moneyStorage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CreditCard> creditCardList = new ArrayList<>();
	
	// Method to fetch savingList separately when needed
	public List<Saving> fetchSavingList() {
		Hibernate.initialize(savingList);
		return savingList;
	}

	// Method to fetch creditCardList separately when needed
	public List<CreditCard> fetchCreditCardList() {
		Hibernate.initialize(creditCardList);
		return creditCardList;
	}

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

	public MoneyStorageTypeEnum getType() {
		return type;
	}

	public void setType(MoneyStorageTypeEnum type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Saving> getSavingList() {
		return savingList;
	}

	public void setSavingList(List<Saving> savingList) {
		this.savingList = savingList;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<CreditCard> getCreditCardList() {
		return creditCardList;
	}

	public void setCreditCardList(List<CreditCard> creditCardList) {
		this.creditCardList = creditCardList;
	}

}
