package com.ainal.apps.wise_spends.common.domain.userType;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YesNoUserType implements UserType<Boolean>, ParameterizedType {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final int SQL_TYPE = Types.VARCHAR;

	private String trueValue = "Y";
	private String falseValue = "N";
	private int length = 1;

	@Override
	public int getSqlType() {
		return SQL_TYPE;
	}

	@Override
	public Class<Boolean> returnedClass() {
		return Boolean.class;
	}

	@Override
	public boolean equals(Boolean x, Boolean y) {
		return (x == y) || (x != null && x.equals(y));
	}

	@Override
	public int hashCode(Boolean x) {
		return x == null ? 0 : x.hashCode();
	}

	@Override
	public Boolean nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		String s = rs.getString(position);
		if (rs.wasNull() || StringUtils.isBlank(s)) {
			return null;
		}
		s = s.trim();
		if (s.equalsIgnoreCase(trueValue)) {
			return Boolean.TRUE;
		}
		if (s.equalsIgnoreCase(falseValue)) {
			return Boolean.FALSE;
		}
		throw new IllegalStateException("Unrecognized boolean value: " + s);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Boolean value, int index,
			SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
			st.setNull(index, SQL_TYPE);
		} else {
			String s = Boolean.TRUE.equals(value) ? trueValue : falseValue;
			if (length > s.length()) {
				s = StringUtils.rightPad(s, length);
			}
			st.setString(index, s);
		}
	}

	@Override
	public Boolean deepCopy(Boolean value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Boolean value) {
		return value;
	}

	@Override
	public Boolean assemble(Serializable cached, Object owner) {
		return (Boolean) cached;
	}

	@Override
	public Boolean replace(Boolean original, Boolean target, Object owner) {
		return original;
	}

	@Override
	public void setParameterValues(Properties parameters) {
		if (parameters != null) {
			String tv = parameters.getProperty("trueValue");
			String fv = parameters.getProperty("falseValue");
			String len = parameters.getProperty("length");
			if (tv != null)
				trueValue = tv;
			if (fv != null)
				falseValue = fv;
			if (len != null) {
				try {
					length = Integer.parseInt(len);
				} catch (NumberFormatException e) {
					LOGGER.warn("Invalid length '{}', using default {}", len, length);
				}
			}
		}
	}
}
