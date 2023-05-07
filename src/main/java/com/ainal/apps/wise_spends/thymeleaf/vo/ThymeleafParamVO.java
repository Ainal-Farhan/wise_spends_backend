package com.ainal.apps.wise_spends.thymeleaf.vo;

import com.ainal.apps.wise_spends.util.properties.ThymeleafPropertiesUtils;

import io.micrometer.common.lang.NonNull;

public class ThymeleafParamVO extends ThymeleafParamAttributeVO {

	public ThymeleafParamVO() {
		super();
	}

	public ThymeleafParamVO(String rawString, @NonNull final ThymeleafPropertiesUtils thymeleafPropertiesUtils) {
		super(rawString, thymeleafPropertiesUtils);
	}
}
