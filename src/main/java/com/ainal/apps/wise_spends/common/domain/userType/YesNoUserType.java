package com.ainal.apps.wise_spends.common.domain.userType;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class YesNoUserType implements UserType<Boolean> {

	private static final int SQL_TYPE = Types.VARCHAR;

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
		return (x == y) || (x != null && y != null && x.equals(y));
	}

	@Override
	public int hashCode(Boolean x) {
		return x.hashCode();
	}

	@Override
	public Boolean nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		String value = rs.getNString(position);
		if (rs.wasNull()) {
			return null;
		}
		return "Y".equals(value);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Boolean value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		} else {
			st.setString(index, ((Boolean) value) ? "Y" : "N");
		}
	}

	@Override
	public Boolean deepCopy(Boolean value) {
		return value == null ? null : Boolean.TRUE.equals(value) ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Boolean value) {
		return (Serializable) value;
	}

	@Override
	public Boolean assemble(Serializable cached, Object owner) {
		return Boolean.valueOf(cached.toString());
	}

	@Override
	public Boolean replace(Boolean original, Boolean target, Object owner) {
		return original;
	}
}
