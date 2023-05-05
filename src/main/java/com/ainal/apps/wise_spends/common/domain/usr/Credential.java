/**
 * 
 */
package com.ainal.apps.wise_spends.common.domain.usr;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;
import com.ainal.apps.wise_spends.common.domain.constant.TablePrefixConstant;
import com.ainal.apps.wise_spends.common.domain.userType.YesNoUserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * @author ainal
 *
 */
@Entity
@Table(name = TablePrefixConstant.USR_TABLE_PREFIX + "CREDENTIAL")
public class Credential extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentialSeq")
	@SequenceGenerator(name = "credentialSeq", sequenceName = "SEQ_CREDENTIAL", allocationSize = 1)
	@Column(name = "CREDENTIAL_ID")
	private Long id;

	@Column(name = "USERNAME", length = 20)
	private String username;

	@Column(name = "ENCRYPTED_PASSWORD", nullable = false)
	private String encryptedPassword;

	@Column(name = "FLAG_USERNAME_IS_EMAIL", columnDefinition = "CHAR(1) DEFAULT 'N'")
	@Type(value = YesNoUserType.class)
	private Boolean flagUsernameIsEmail = Boolean.FALSE;

	@Column(name = "FLAG_ACTIVE", columnDefinition = "CHAR(1) DEFAULT 'Y'")
	@Type(value = YesNoUserType.class)
	private Boolean flagActive = Boolean.TRUE;

	@OneToOne(mappedBy = "credential", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private User user;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Boolean getFlagUsernameIsEmail() {
		return flagUsernameIsEmail;
	}

	public void setFlagUsernameIsEmail(Boolean flagUsernameIsEmail) {
		this.flagUsernameIsEmail = flagUsernameIsEmail;
	}

	public Boolean getFlagActive() {
		return flagActive;
	}

	public void setFlagActive(Boolean flagActive) {
		this.flagActive = flagActive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
