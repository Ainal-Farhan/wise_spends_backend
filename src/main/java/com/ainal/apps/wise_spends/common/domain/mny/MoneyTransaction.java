package com.ainal.apps.wise_spends.common.domain.mny;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.Type;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;
import com.ainal.apps.wise_spends.common.domain.userType.YesNoUserType;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.reference.MoneyTransactionTypeEnum;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = TablePrefixConstant.MNY_TABLE_PREFIX + "MONEY_TRANSACTION")
public class MoneyTransaction extends BaseEntity {

	private static final long serialVersionUID = 3979287216121809164L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moneyTransactionSeq")
	@SequenceGenerator(name = "moneyTransactionSeq", sequenceName = "SEQ_MONEY_TRANSACTION", allocationSize = 1)
	@Column(name = "MONEY_TRANSACTION_ID")
	private Long id;

	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private MoneyTransactionTypeEnum type;

	@Column(name = "FROM_ID", nullable = false)
	private Long fromId;

	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;

	@Column(name = "TRANSACTION_DATE", nullable = false)
	private Date transactionDate;

	@Column(name = "FLAG_CREDIT_CARD", columnDefinition = "CHAR(1) DEFAULT 'N'")
	@Type(value = YesNoUserType.class)
	private Boolean flagCreditCard = Boolean.FALSE;

	@Column(name = "FLAG_MONEY_STORAGE", columnDefinition = "CHAR(1) DEFAULT 'Y'")
	@Type(value = YesNoUserType.class)
	private Boolean flagMoneyStorage = Boolean.TRUE;

	@Column(name = "FLAG_SAVING", columnDefinition = "CHAR(1) DEFAULT 'N'")
	@Type(value = YesNoUserType.class)
	private Boolean flagSaving = Boolean.FALSE;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "MONEY_TRANSACTION_REFERENCE_ID", foreignKey = @ForeignKey(name = "FK_MT_MNY_MONEY_TRANSACTION_REFERENCE_ID"))
	private MoneyTransactionReference moneyTransactionReference;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_MT_MNY_USER_ID"))
	private User user;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public MoneyTransactionTypeEnum getType() {
		return type;
	}

	public void setType(MoneyTransactionTypeEnum type) {
		this.type = type;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public MoneyTransactionReference getMoneyTransactionReference() {
		return moneyTransactionReference;
	}

	public void setMoneyTransactionReference(MoneyTransactionReference moneyTransactionReference) {
		this.moneyTransactionReference = moneyTransactionReference;
	}

	public Boolean getFlagCreditCard() {
		return flagCreditCard;
	}

	public void setFlagCreditCard(Boolean flagCreditCard) {
		this.flagCreditCard = flagCreditCard;
	}

	public Boolean getFlagMoneyStorage() {
		return flagMoneyStorage;
	}

	public void setFlagMoneyStorage(Boolean flagMoneyStorage) {
		this.flagMoneyStorage = flagMoneyStorage;
	}

	public Boolean getFlagSaving() {
		return flagSaving;
	}

	public void setFlagSaving(Boolean flagSaving) {
		this.flagSaving = flagSaving;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
