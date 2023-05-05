package com.ainal.apps.wise_spends.common.domain.usr;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.domain.userType.YesNoUserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = TablePrefixConstant.USR_TABLE_PREFIX + "ROLE")
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
	@SequenceGenerator(name = "roleSeq", sequenceName = "SEQ_ROLE", allocationSize = 1)
	@Column(name = "ROLE_ID")
	private Long id;

	@Column(name = "FLAG_ACTIVE", columnDefinition = "CHAR(1) DEFAULT 'Y'")
	@Type(value = YesNoUserType.class)
	private Boolean flagActive = Boolean.TRUE;

	@Column(name = "FLAG_MAIN_ROLE", columnDefinition = "CHAR(1) DEFAULT 'N'")
	@Type(value = YesNoUserType.class)
	private Boolean flagMainRole = Boolean.FALSE;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "USER_ROLE_ID", foreignKey = @ForeignKey(name = "FK_UR_REF_USER_ROLE_ID"))
	private UserRole userRole;

	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_UR_USR_USER_ID"))
	private User user;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFlagActive() {
		return flagActive;
	}

	public void setFlagActive(Boolean flagActive) {
		this.flagActive = flagActive;
	}

	public Boolean getFlagMainRole() {
		return flagMainRole;
	}

	public void setFlagMainRole(Boolean flagMainRole) {
		this.flagMainRole = flagMainRole;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
