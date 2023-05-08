package com.ainal.apps.wise_spends.common.domain.mny;

import java.math.BigDecimal;
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
@Table(name = TablePrefixConstant.MNY_TABLE_PREFIX + "MONEY_STORAGE")
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
	private String type;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_MMS_MNY_USER_ID"))
	private User user;

	@OneToMany(mappedBy = "moneyStorage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Saving> savingList = new ArrayList<>();

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

}
