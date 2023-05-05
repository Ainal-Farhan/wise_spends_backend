package com.ainal.apps.wise_spends.util.validator;

import java.util.regex.Pattern;

import io.micrometer.common.util.StringUtils;

public class EmailValidator {
	private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

	public static boolean isValidEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}

		return EMAIL_PATTERN.matcher(email).matches();
	}
}
