package com.ainal.apps.wise_spends.common.domain.access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.userType.YesNoUserType;
import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

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
@Table(name = TablePrefixConstant.ACS_TABLE_PREFIX + "SIDEBAR_MENU")
public class SidebarMenu extends BaseEntity {

	private static final long serialVersionUID = -339666153121655053L;
	private static final String FK_PREFIX = "FK_ASM_" + TablePrefixConstant.ACS_TABLE_PREFIX;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sidebarMenuSeq")
	@SequenceGenerator(name = "sidebarMenuSeq", sequenceName = "SEQ_SIDEBAR_MENU", allocationSize = 1)
	@Column(name = "SIDEBAR_MENU_ID")
	private Long id;

	@Column(name = "TITLE", nullable = false, unique = true)
	private String title;

	@Column(name = "SEQUENCE", nullable = false)
	private Integer sequence;

	@Column(name = "URL", length = 100)
	private String url;

	@Column(name = "TOOLTIP", length = 200)
	private String tooltip;

	@Column(name = "CONTEXT_PATH", length = 200)
	private String contextPath;

	@Column(name = "FLAG_PERMANENT_SIDEBAR_MENU", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
	@Type(value = YesNoUserType.class)
	private Boolean flagPermanentSidebarMenu = Boolean.FALSE;

	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JoinColumn(name = "PARENT_ID", foreignKey = @ForeignKey(name = FK_PREFIX + "PARENT_ID"))
	private SidebarMenu parent;

	@OneToMany(mappedBy = "sidebarMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roleList = new ArrayList<>();

	@OneToMany(mappedBy = "parent")
	private List<SidebarMenu> children = new ArrayList<>();

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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public Boolean getFlagPermanentSidebarMenu() {
		return flagPermanentSidebarMenu;
	}

	public void setFlagPermanentSidebarMenu(Boolean flagPermanentSidebarMenu) {
		this.flagPermanentSidebarMenu = flagPermanentSidebarMenu;
	}

	public SidebarMenu getParent() {
		return parent;
	}

	public void setParent(SidebarMenu parent) {
		this.parent = parent;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<SidebarMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SidebarMenu> children) {
		this.children = children;
	}

}
