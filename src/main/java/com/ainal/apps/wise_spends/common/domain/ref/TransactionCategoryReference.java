package com.ainal.apps.wise_spends.common.domain.ref;

import java.util.ArrayList;
import java.util.List;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = TablePrefixConstant.REF_TABLE_PREFIX + "TRANSACTION_CATEGORY_REFERENCE")
public class TransactionCategoryReference extends BaseEntity {
	private static final long serialVersionUID = 8545024613827193474L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionCategoryReferenceSeq")
	@SequenceGenerator(name = "transactionCategoryReferenceSeq", sequenceName = "SEQ_TRANSACTION_CATEGORY_REFERENCE", allocationSize = 1)
	@Column(name = "TRANSACTION_CATEGORY_REFERENCE_ID")
	private Long id;

	@Column(name = "TITLE", nullable = false, unique = true)
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "transactionCategoryReference", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TransactionReference> transactionReferenceList = new ArrayList<>();

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

	public List<TransactionReference> getTransactionReferenceList() {
		return transactionReferenceList;
	}

	public void setTransactionReferenceList(List<TransactionReference> transactionReferenceList) {
		this.transactionReferenceList = transactionReferenceList;
	}

}
