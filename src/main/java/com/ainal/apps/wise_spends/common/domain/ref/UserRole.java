package com.ainal.apps.wise_spends.common.domain.ref;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.userType.YesNoUserType;
import com.ainal.apps.wise_spends.common.domain.usr.Role;

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
@Table(name = TablePrefixConstant.REF_TABLE_PREFIX + "USER_ROLE")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRoleSeq")
	@SequenceGenerator(name = "userRoleSeq", sequenceName = "SEQ_USER_ROLE", allocationSize = 1)
	@Column(name = "USER_ROLE_ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "CODE", nullable = false, unique = true)
	private String code;

	@Column(name = "FLAG_ACTIVE", columnDefinition = "CHAR(1) DEFAULT 'Y'")
	@Type(value = YesNoUserType.class)
	private Boolean flagActive = Boolean.TRUE;

	@OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roleList = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
}
