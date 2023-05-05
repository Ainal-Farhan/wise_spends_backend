/**
 * 
 */
package com.ainal.apps.wise_spends.common.domain.base;

import java.beans.PropertyDescriptor;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.ainal.apps.wise_spends.util.logging.GenericLogger;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

/**
 * @author ainal
 *
 */
@jakarta.persistence.MappedSuperclass
public abstract class BaseEntity
		implements IBaseEntity, java.io.Serializable, org.hibernate.engine.spi.ManagedMappedSuperclass {
	private static final long serialVersionUID = 1L;

	private static final java.lang.String TOSTRING_TEMPLATE = "{0}:{1}";
	protected static final GenericLogger LOGGER = new GenericLogger(BaseEntity.class);

	@Version
	@Column(name = "VERSION", nullable = false)
	private Long version;

	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy;

	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "LAST_MODIFIED_BY", nullable = false)
	private String lastModifiedBy;

	@Column(name = "LAST_MODIFIED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	public BaseEntity() {
		super();
	}

	@Transient
	public boolean shouldAudit() {
		return true;
	}

	@Override
	public int hashCode() {
		return 13;
	}

	@Override
	public boolean equals(Object obj) {
		if (getId() == null) {
			int hashcode1 = System.identityHashCode(this);
			int hashcode2 = System.identityHashCode(obj);
			if (hashcode1 != hashcode2) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			BaseEntity other = (BaseEntity) obj;
			if (getId() == null) {
				if (other.getId() != null) {
					return false;
				}
			} else if (!getId().equals(other.getId())) {
				return false;
			}
		} else if (!getId().equals(((BaseEntity) obj).getId())) {
			return false;
		}
		return true;
	}

	public String toString() {
		return MessageFormat.format(TOSTRING_TEMPLATE, getClass().getSimpleName(), getId());
	}

	public String print() {
		String delimiter = ", ";
		String entityFormat = "(id={0})";
		StringBuilder sb = new StringBuilder("{");
		PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(this);
		PropertyDescriptor property;
		Object value;
		for (int i = 0; i < properties.length; i++) {
			property = properties[i];
			sb.append(property.getName()).append('=');
			try {
				value = PropertyUtils.getProperty(this, property.getName());
				if (value instanceof BaseEntity) {
					BaseEntity entityValue = (BaseEntity) value;
					String objectValueString = MessageFormat.format(entityFormat, entityValue.getId());
					sb.append(objectValueString);
				} else {
					sb.append(value);
				}
			} catch (Exception e) {
				LOGGER.error(e);
			}
			sb.append(delimiter);
		}
		sb.deleteCharAt(sb.length() - delimiter.length());
		sb.append('}');
		return sb.toString();
	}

	public boolean isNew() {
		if (this.getId() == null) {
			return true;
		}
		return false;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
