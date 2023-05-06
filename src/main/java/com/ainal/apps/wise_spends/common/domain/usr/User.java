package com.ainal.apps.wise_spends.common.domain.usr;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.userType.YesNoUserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = TablePrefixConstant.USR_TABLE_PREFIX + "USER")
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
	@SequenceGenerator(name = "userSeq", sequenceName = "SEQ_USER", allocationSize = 1)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "FLAG_ACTIVE", columnDefinition = "CHAR(1) DEFAULT 'Y'")
	@Type(value = YesNoUserType.class)
	private Boolean flagActive = Boolean.TRUE;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREDENTIAL_ID", unique = true, foreignKey = @ForeignKey(name = "FK_UU_USR_CRE_ID"))
	private Credential credential;

	@OneToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JoinColumn(name = "INDIVIDUAL_ID", unique = true, foreignKey = @ForeignKey(name = "FK_UU_USR_IND_ID"))
	private Individual individual;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roleList = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MoneyStorage> moneyStorageList = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public Individual getIndividual() {
		return individual;
	}

	public void setIndividual(Individual individual) {
		this.individual = individual;
	}

	public Boolean getFlagActive() {
		return flagActive;
	}

	public void setFlagActive(Boolean flagActive) {
		this.flagActive = flagActive;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<MoneyStorage> getMoneyStorageList() {
		return moneyStorageList;
	}

	public void setMoneyStorageList(List<MoneyStorage> moneyStorageList) {
		this.moneyStorageList = moneyStorageList;
	}

}
