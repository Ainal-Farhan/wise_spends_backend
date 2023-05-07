package com.ainal.apps.wise_spends.thymeleaf.vo.fragment;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import com.ainal.apps.wise_spends.util.properties.ThymeleafPropertiesUtils;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;

public class ThymeleafParamVO {
	private String name;
	private Object value;
	private String type;

	private static final String SEPERATOR_DATA_TYPE = "::";
	private static final String SEPERATOR_DEFAULT_CONTENT = ":";

	public ThymeleafParamVO() {
		initDefault();
	}

	public ThymeleafParamVO(String rawString, @NonNull final ThymeleafPropertiesUtils thymeleafPropertiesUtils) {
		if (StringUtils.isBlank(rawString)) {
			initDefault();
			return;
		}

		initFromString(rawString, thymeleafPropertiesUtils);
	}

	private void initDefault() {
		name = Strings.EMPTY;
		value = Strings.EMPTY;
		type = Strings.EMPTY;
	}

	private void initFromString(String rawString, @NonNull final ThymeleafPropertiesUtils thymeleafPropertiesUtils) {
		List<String> contentAndType = Arrays.asList(rawString.split(SEPERATOR_DATA_TYPE));

		if (CollectionUtils.isEmpty(contentAndType) || contentAndType.size() != 2) {
			initDefault();
			return;
		}

		final String type = contentAndType.get(1);

		List<String> nameAndContent = Arrays.asList(contentAndType.get(0).split(SEPERATOR_DEFAULT_CONTENT));

		if (CollectionUtils.isEmpty(nameAndContent) || nameAndContent.size() != 2) {
			initDefault();
			return;
		}

		final String name = nameAndContent.get(0);
		final String defaultValue = nameAndContent.get(1);
		Object value = null;

		if (thymeleafPropertiesUtils.getStringDataType().equals(type)) {
			value = defaultValue;
		} else if (thymeleafPropertiesUtils.getBooleanDataType().equals(type)) {
			value = Boolean.valueOf(defaultValue);
		}

		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name + SEPERATOR_DEFAULT_CONTENT + value + SEPERATOR_DATA_TYPE + type;
	}
}
