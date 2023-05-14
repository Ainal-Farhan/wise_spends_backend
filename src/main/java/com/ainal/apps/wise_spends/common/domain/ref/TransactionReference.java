package com.ainal.apps.wise_spends.common.domain.ref;

import java.util.ArrayList;
import java.util.List;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.mny.Transaction;

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
@Table(name = TablePrefixConstant.REF_TABLE_PREFIX + "TRANSACTION_REFERENCE")
public class TransactionReference extends BaseEntity {
	private static final long serialVersionUID = 3054605065392848908L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionReferenceSeq")
	@SequenceGenerator(name = "transactionReferenceSeq", sequenceName = "SEQ_TRANSACTION_REFERENCE", allocationSize = 1)
	@Column(name = "TRANSACTION_REFERENCE_ID")
	private Long id;

	@Column(name = "TITLE", nullable = false, unique = true)
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CODE", nullable = false, unique = true)
	private String code;

	@OneToMany(mappedBy = "transactionReference", fetch = FetchType.LAZY)
	private List<Transaction> transactionList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACTION_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_RTR_REF_TCR_ID"), nullable = false)
	private TransactionCategoryReference transactionCategoryReference;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public TransactionCategoryReference getTransactionCategoryReference() {
		return transactionCategoryReference;
	}

	public void setTransactionCategoryReference(TransactionCategoryReference transactionCategoryReference) {
		this.transactionCategoryReference = transactionCategoryReference;
	}

}
