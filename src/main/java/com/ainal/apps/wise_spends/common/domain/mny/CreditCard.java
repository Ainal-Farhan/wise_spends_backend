package com.ainal.apps.wise_spends.common.domain.mny;

import java.math.BigDecimal;
import java.util.Date;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;

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
@Table(name = TablePrefixConstant.MNY_TABLE_PREFIX + "CREDIT_CARD")
public class CreditCard extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardCreditSeq")
	@SequenceGenerator(name = "cardCreditSeq", sequenceName = "SEQ_CARD_CREDIT", allocationSize = 1)
	@Column(name = "CARD_CREDIT_ID")
	private Long id;

	@Column(name = "SHORT_NAME")
	private String shortName;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "ABBREVIATION")
	private String abbreviation;

	@Column(name = "CREDIT_AMOUNT")
	private BigDecimal creditAmount;

	@Column(name = "OUTSTANDING_BALANCE")
	private BigDecimal outstandingBalance;

	@Column(name = "MININUM_PAYMENT")
	private BigDecimal minimumPayment;

	@Column(name = "STATEMENT_BALANCE")
	private BigDecimal statementBalance;

	@Column(name = "CREDIT_LIMIT")
	private BigDecimal creditLimit;

	@Column(name = "STATEMENT_DATE")
	private Date statementDate;

	@Column(name = "PAYMENT_DUE_DATE")
	private Date paymentDueDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "MONEY_STORAGE_ID", foreignKey = @ForeignKey(name = "FK_MS_MNY_CREDIT_CARD_ID"))
	private MoneyStorage moneyStorage;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public BigDecimal getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(BigDecimal outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public BigDecimal getMinimumPayment() {
		return minimumPayment;
	}

	public void setMinimumPayment(BigDecimal minimumPayment) {
		this.minimumPayment = minimumPayment;
	}

	public BigDecimal getStatementBalance() {
		return statementBalance;
	}

	public void setStatementBalance(BigDecimal statementBalance) {
		this.statementBalance = statementBalance;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Date getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(Date statementDate) {
		this.statementDate = statementDate;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public MoneyStorage getMoneyStorage() {
		return moneyStorage;
	}

	public void setMoneyStorage(MoneyStorage moneyStorage) {
		this.moneyStorage = moneyStorage;
	}
}
